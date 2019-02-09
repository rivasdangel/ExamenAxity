package mx.leonardorivasd.examen_axity;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

public class GetData extends AsyncTask{
    private TextView tvProducto;
    private TextView tvDepartamento;
    private TextView tvId;
    private TextView tvPrecio;
    private Context context;


    public GetData(TextView tvProducto, TextView tvDepartamento, TextView tvId, TextView tvPrecio, Context context) {
        this.tvProducto = tvProducto;
        this.tvDepartamento = tvDepartamento;
        this.tvId = tvId;
        this.tvPrecio = tvPrecio;
        this.context = context;
    }



    @Override
    protected Object doInBackground(Object[] objects) {
        URL githubEndpoint = null;
        try {
            githubEndpoint = new URL("https://super.walmart.com.mx/api/rest/model/atg/commerce/catalog/ProductCatalogActor/getSkuSummaryDetails?storeId=0000009999&upc=00750129560012&skuId=00750129560012");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        // Create connection
        try {
            HttpsURLConnection myConnection = (HttpsURLConnection) githubEndpoint.openConnection();
            myConnection.setRequestProperty("accept", "application/json'");
            myConnection.setRequestProperty("connection", "keep-alive");
            myConnection.setRequestProperty("content-type", "application/json");
            if (myConnection.getResponseCode() == HttpURLConnection.HTTP_OK) {//HTTP_OK code is 200
                InputStream responseBody = myConnection.getInputStream();
                InputStreamReader responseBodyReader = new InputStreamReader(responseBody, "UTF-8");
                BufferedReader streamReader = new BufferedReader(responseBodyReader);
                StringBuilder responseStrBuilder = new StringBuilder();

                //get JSON String
                String inputStr;
                while ((inputStr = streamReader.readLine()) != null)
                    responseStrBuilder.append(inputStr);

                myConnection.disconnect();
                return responseStrBuilder.toString();
            } else {
                Log.d("Main", "error in connection");
                return "";
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(Object o) {
        super.onPostExecute(o);
        try {
            JSONObject jsonObject = new JSONObject(o.toString());
            this.tvProducto.setText(jsonObject.get("skuDisplayNameText").toString());
            this.tvDepartamento.setText(jsonObject.get("department").toString());
            this.tvId.setText(jsonObject.getJSONObject("sku").get("id").toString());
            this.tvPrecio.setText(jsonObject.get("basePrice").toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
