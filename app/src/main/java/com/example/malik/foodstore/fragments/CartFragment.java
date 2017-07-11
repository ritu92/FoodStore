package com.example.malik.foodstore.fragments;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.Html;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.malik.foodstore.FragmentChangeListener;
import com.example.malik.foodstore.R;
import com.example.malik.foodstore.activities.MapsActivity;
import com.example.malik.foodstore.adapters.CartAdapter;
import com.example.malik.foodstore.model.CartList;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.BooleanResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.wallet.Cart;
import com.google.android.gms.wallet.FullWallet;
import com.google.android.gms.wallet.FullWalletRequest;
import com.google.android.gms.wallet.LineItem;
import com.google.android.gms.wallet.MaskedWallet;
import com.google.android.gms.wallet.MaskedWalletRequest;
import com.google.android.gms.wallet.NotifyTransactionStatusRequest;
import com.google.android.gms.wallet.PaymentMethodTokenizationParameters;
import com.google.android.gms.wallet.PaymentMethodTokenizationType;
import com.google.android.gms.wallet.Wallet;
import com.google.android.gms.wallet.WalletConstants;
import com.google.android.gms.wallet.fragment.BuyButtonText;
import com.google.android.gms.wallet.fragment.Dimension;
import com.google.android.gms.wallet.fragment.SupportWalletFragment;
import com.google.android.gms.wallet.fragment.WalletFragmentInitParams;
import com.google.android.gms.wallet.fragment.WalletFragmentMode;
import com.google.android.gms.wallet.fragment.WalletFragmentOptions;
import com.google.android.gms.wallet.fragment.WalletFragmentStyle;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

/**
 * Created by malik on 7/4/2017.
 * populates view for cart fragment\
 * enables on click listener on items
 * enables android pay wallet payment
 * enables button to get current location via maps activity
 * @see GoogleApiClient
 * @see MaskedWallet
 */

public class CartFragment extends Fragment implements
        GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener {
    private SupportWalletFragment mWalletFragment;
    public static final int MASKED_WALLET_REQUEST_CODE = 888;
    public static final String WALLET_FRAGMENT_ID = "wallet_fragment";
    private MaskedWallet mMaskedWallet;
    private GoogleApiClient mGoogleApiClient;
    public static final int FULL_WALLET_REQUEST_CODE = 889;
    private FullWallet mFullWallet;
    Button bt_location,  buttonCash, bt_Confirm;
    EditText et_Address, editText;
    String address, name, price;
    RecyclerView recyclerViewCart;
    RecyclerView.Adapter adapter;
    ArrayList<CartList> ar = new ArrayList<>();

    /**
     * view inflator for the cart
     * gets items selected from other fragments to be put in cart via shared preferences
     * gets current address from google maps activity
     * @see SharedPreferences
     * @param inflater
     * @param container
     * @param savedInstanceState
     * @return view for the fragment
     */
    @Override
    public View onCreateView(LayoutInflater inflater,  ViewGroup container,  Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.cart_fragment, container, false);
        editText = (EditText) view.findViewById(R.id.editText);
        bt_location = (Button)view.findViewById(R.id.bt_location);
        buttonCash = (Button)view.findViewById(R.id.buttonCash);
        bt_Confirm = (Button)view.findViewById(R.id.bt_Confirm);
        et_Address =(EditText) view.findViewById(R.id.et_Address);
        SharedPreferences pref = getActivity().getApplicationContext().getSharedPreferences("MyPref", 0);
        address = pref.getString("address",null);
        SharedPreferences pref2 = getActivity().getApplicationContext().getSharedPreferences("MyPrefCart", 0);

        price = pref2.getString("price",null);
        name = pref2.getString("name", null);
        editText.setText(name +"  "+price);
        recyclerViewCart = (RecyclerView) view.findViewById(R.id.recyclerViewCart);
        CartList pl = new CartList(name, price);
        Log.i("Ritu2", pl.toString()+"cart"+name+price);
        ar.add(pl);

        adapter = new CartAdapter(ar, getActivity().getApplicationContext());
        recyclerViewCart.setAdapter(adapter);
        /**
         * displays current address received from google maps activity
         * if we don't want to enter manual address
         */
        et_Address.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("Ritu", address);
                et_Address.setText(address);
            }
        });
/**
 * takes us to the maps activity to fetch the current address
 */
        bt_location.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), MapsActivity.class);
                startActivity(intent);

            }
        });
        bt_Confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                requestFullWallet(v);
            }
        });
        /**
         * places the order of cart items if you want cash on delivery so no payment has to be made
         */
        buttonCash.setOnClickListener(new View.OnClickListener() {
            Calendar c = Calendar.getInstance();
            SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy");
            String formattedDate = df.format(c.getTime());
            @Override
            public void onClick(View v) {
                Log.i("Ritu", formattedDate);
                Log.i("Ritu", name+" "+price);
                String ORDER_URL = "http://rjtmobile.com/ansari/fos/fosapp/order_request.php?&order_category=veg&order_name="+name+"&order_quantity=1&total_order="+price+"&order_delivery_add=noida&order_date="+formattedDate+"&user_phone=55565454";
                StringRequest stringRequest = new StringRequest(Request.Method.POST, ORDER_URL,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {

                                if (response.contains("order confirmed")) {
                                    Toast.makeText(getActivity().getApplicationContext(), "Order placed for num:55565454" , Toast.LENGTH_LONG).show();


                                } else
                                    Toast.makeText(getActivity().getApplicationContext(), response + "Order unsuccessful", Toast.LENGTH_LONG).show();
                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                Toast.makeText(getActivity().getApplicationContext(), error.toString(), Toast.LENGTH_LONG).show();
                            }
                        });
                RequestQueue requestQueue = Volley.newRequestQueue(getActivity().getApplicationContext());
                requestQueue.add(stringRequest);

            }
        });
        // 3 food items 1000 rupees
        generateMaskedWalletRequest(3, 1000);

        // Wallet fragment style
        WalletFragmentStyle walletFragmentStyle = new WalletFragmentStyle()
                .setBuyButtonText(BuyButtonText.BUY_NOW)
                .setBuyButtonWidth(Dimension.MATCH_PARENT);

        // Wallet fragment options
        WalletFragmentOptions walletFragmentOptions = WalletFragmentOptions.newBuilder()
                .setEnvironment(WalletConstants.ENVIRONMENT_SANDBOX)
                .setFragmentStyle(walletFragmentStyle)
                .setTheme(WalletConstants.THEME_LIGHT)
                .setMode(WalletFragmentMode.BUY_BUTTON)
                .build();

        // Instantiate the WalletFragment
        mWalletFragment = SupportWalletFragment.newInstance(walletFragmentOptions);

        // Initialize the WalletFragment
        WalletFragmentInitParams.Builder startParamsBuilder =
                WalletFragmentInitParams.newBuilder()
                        .setMaskedWalletRequest(generateMaskedWalletRequest(2, 300))
                        .setMaskedWalletRequestCode(MASKED_WALLET_REQUEST_CODE)
                        .setAccountName("Hotel Tajmahal");
        mWalletFragment.initialize(startParamsBuilder.build());

        // Add the WalletFragment to the UI
        getChildFragmentManager().beginTransaction()
                .replace(R.id.wallet_button_holder, mWalletFragment, WALLET_FRAGMENT_ID)
                .commit();


        mGoogleApiClient = new GoogleApiClient.Builder(getActivity())
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(Wallet.API, new Wallet.WalletOptions.Builder()
                        .setEnvironment(WalletConstants.ENVIRONMENT_SANDBOX)
                        .setTheme(WalletConstants.THEME_LIGHT)
                        .build())
                .build();

        return view;
    }

    /**
     * masked request for android pay to crate instance of available wallet for payment
     * @param quantity custom parameter for quantity of one item
     * @param price price of one item
     * @return request instance response
     */

    private MaskedWalletRequest generateMaskedWalletRequest(int quantity, int price) {
        MaskedWalletRequest maskedWalletRequest =
                MaskedWalletRequest.newBuilder()
                        .setMerchantName("Hotel Tajmahal")
                        .setPhoneNumberRequired(true)
                        .setShippingAddressRequired(true)
                        .setCurrencyCode("Rupee")
                        .setCart(Cart.newBuilder()
                                .setCurrencyCode("Rupee")
                                .setTotalPrice("333.00")   // all items cart total
                                .addLineItem(LineItem.newBuilder()
                                        .setCurrencyCode("Rupee")
                                        .setDescription("Matar paneer")
                                        .setQuantity(quantity + "")
                                        .setUnitPrice(price + "")
                                        .setTotalPrice("400")  // 1st items all quantity total
                                        .build())
                                .addLineItem(LineItem.newBuilder()
                                        .setCurrencyCode("Rupee")
                                        .setDescription("")
                                        .setQuantity(quantity + "")
                                        .setUnitPrice(price + "")
                                        .setTotalPrice("300")  // 2nd item all quantity total
                                        .build())
                                .build())
                        .setEstimatedTotalPrice("1000")       // all items cart total with tax
                        .build();
        return maskedWalletRequest;
    }

    /**
     * cases for both masked wallet result and full wallet result received by activity
     * various actions we will take according to response received stated here
     * @param requestCode
     * @param resultCode
     * @param data returned by intent service
     */
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case MASKED_WALLET_REQUEST_CODE:
                switch (resultCode) {
                    case Activity.RESULT_OK:
                        mMaskedWallet =  data
                                .getParcelableExtra(WalletConstants.EXTRA_MASKED_WALLET);
                        Toast.makeText(getActivity(), "Got Masked Wallet", Toast.LENGTH_SHORT).show();
                        break;
                    case Activity.RESULT_CANCELED:
                        // The user canceled the operation
                        break;
                    case WalletConstants.RESULT_ERROR:
                        Toast.makeText(getActivity(), "An Error Occurred", Toast.LENGTH_SHORT).show();
                        break;
                }
                break;

            case FULL_WALLET_REQUEST_CODE:
                switch (resultCode) {
                    case Activity.RESULT_OK:
                        mFullWallet = data
                                .getParcelableExtra(WalletConstants.EXTRA_FULL_WALLET);
                        // Show the credit card number
                        Toast.makeText(getActivity(),
                                mFullWallet.getProxyCard().getPan().toString(),
                                Toast.LENGTH_SHORT).show();
                        break;
                    case WalletConstants.RESULT_ERROR:
                        Toast.makeText(getActivity(), "An Error Occurred", Toast.LENGTH_SHORT).show();
                        break;
                }
                // Update transaction status
                Wallet.Payments.notifyTransactionStatus(mGoogleApiClient,
                        generateNotifyTransactionStatusRequest(
                                mFullWallet.getGoogleTransactionId(),
                                NotifyTransactionStatusRequest.Status.SUCCESS));
                break;
        }
    }

    /**
     * makes a request for instance of full wallet
     * @param googleTransactionId for later reference to print details of our transaction
     * @return full wallet instance response
     */
    private FullWalletRequest generateFullWalletRequest(String googleTransactionId) {
        FullWalletRequest fullWalletRequest = FullWalletRequest.newBuilder()
                .setGoogleTransactionId(googleTransactionId)
                .setCart(Cart.newBuilder()
                        .setCurrencyCode("USD")
                        .setTotalPrice("10.10")
                        .addLineItem(LineItem.newBuilder()
                                .setCurrencyCode("USD")
                                .setDescription("Google I/O Sticker")
                                .setQuantity("1")
                                .setUnitPrice("10.00")
                                .setTotalPrice("10.00")
                                .build())
                        .addLineItem(LineItem.newBuilder()
                                .setCurrencyCode("USD")
                                .setDescription("Tax")
                                .setRole(LineItem.Role.TAX)
                                .setTotalPrice(".10")
                                .build())
                        .build())
                .build();
        return fullWalletRequest;
    }

    /**
     * starting service on stat to save battery
     */
    @Override
    public void onStart() {
        mGoogleApiClient.connect();
        super.onStart();
    }

    /**
     * stopping service when not required
     */
    @Override
    public void onStop() {
        mGoogleApiClient.disconnect();
        super.onStop();
    }

    /**
     * Google Api connection status
     * @param bundle
     */
    @Override
    public void onConnected(Bundle bundle) {
        // GoogleApiClient is connected, we don't need to do anything here
    }

    @Override
    public void onConnectionSuspended(int cause) {
        // GoogleApiClient is temporarily disconnected, no action needed
    }

    @Override
    public void onConnectionFailed(ConnectionResult result) {
        // GoogleApiClient failed to connect, we should log the error and retry
    }

    /**
     * if appropriate wallet instance response not found then let user know
     * otherwise compare instances of masked wallet and full wallet to make final payment
     * @param view
     */
    public void requestFullWallet(View view) {
        if (mMaskedWallet == null) {
                    Toast.makeText(getActivity(), "No masked wallet, can't confirm", Toast.LENGTH_SHORT).show();

                     return;
        }
        Wallet.Payments.loadFullWallet(mGoogleApiClient,
                generateFullWalletRequest(mMaskedWallet.getGoogleTransactionId()),
                FULL_WALLET_REQUEST_CODE);
    }

    /**
     * transaction status notification
     * @param googleTransactionId Id for later reference
     * @param status status of payment
     * @return notification of transaction details
     */
    public static NotifyTransactionStatusRequest generateNotifyTransactionStatusRequest(
            String googleTransactionId, int status) {
        return NotifyTransactionStatusRequest.newBuilder()
                .setGoogleTransactionId(googleTransactionId)
                .setStatus(status)
                .build();
    }

}
