package web;

import com.google.gson.Gson;
import model.Product;
import okhttp3.*;

import java.io.IOException;

public class TestApi {
    public static void main(String[] args) throws IOException {
     testGetProducts();
        testDeleteProduct();
        testAddProduct();
    }

    private static void testAddProduct() throws IOException {
        RequestBody formBody = new FormBody.Builder()
                .add("product_name", "Lalal")
                .add("product_price", "123")
                .build();
        Request request = new Request.Builder()
                .url("http://localhost:8080/api/products/add")
                .post(formBody)
                .build();

        OkHttpClient client = new OkHttpClient();
        Response response = client.newCall(request).execute();
        if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);

        System.out.println(response.body().string());

    }

    private static void testDeleteProduct() throws IOException {
        RequestBody formBody = new FormBody.Builder()
                .add("product_id", "8")
                .build();
        Request request = new Request.Builder()
                .url("http://localhost:8080/api/products/delete")
                .post(formBody)
                .build();

        OkHttpClient client = new OkHttpClient();
        Response response = client.newCall(request).execute();
        if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);

        System.out.println(response.body().string());
    }

    private static void testGetProducts() throws IOException {
        String jsonString = getUrl("http://localhost:8080/api/products/");
        Product[] products = new Gson().fromJson(jsonString, Product[].class);
        for (Product product : products) {
            System.out.println(product.name + " " + product.price);
        }
    }

    private static String getUrl(String url) throws IOException {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(url)
                .build();
        Response response = client.newCall(request).execute();
        return response.body().string();
    }
}
