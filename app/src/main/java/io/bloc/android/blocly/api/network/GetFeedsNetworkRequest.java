package io.bloc.android.blocly.api.network;

import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Created by benwong on 2015-05-13.
 */
public class GetFeedsNetworkRequest extends NetworkRequest {

    String[] feedUrls;

    public GetFeedsNetworkRequest(String... feedUrls){
        this.feedUrls = feedUrls;
    }

    @Override
    public Object performRequest(){
        for (String feedUrlString : feedUrls){
            InputStream inputStream = openStream (feedUrlString);
            if (inputStream == null){
                return null;
            }
            try{
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

                String line = bufferedReader.readLine();
                int count = 0;
                while (line !=null){
                    if (line.contains("item")){
                        count++;
                    }
                    Log.v(getClass().getSimpleName(), "Item: " + count );
                    Log.v(getClass().getSimpleName(), "Line: " + line);
                    line = bufferedReader.readLine();
                }

                bufferedReader.close();

            } catch (IOException e){
                e.printStackTrace();
                setErrorCode(ERROR_IO);
                return null;
            }
        }
        return null;
    }

}
