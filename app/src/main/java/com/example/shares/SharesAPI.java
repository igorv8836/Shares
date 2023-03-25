package com.example.shares;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface SharesAPI {

    String BASE_API = "https://finnhub.io/";
    String API_KEY = "cfpjbr9r01qq927hih3gcfpjbr9r01qq927hih40";

    String[] NAME = {"TSLA", "NVDA", "AAPL", "AMD", "INTC", "MSFT", "NFLX", "ZM", "FAST", "AMGN"};
    String[] FULL_NAME = {"Tesla Inc", "NVIDIA Corporation", "Apple Inc", "Advanced Micro Devices Inc", "Intel Corporation", "Microsoft Corporation", "Netflix Inc", "Zoom Video Communications Inc", "Fastenal Company", "Amgen Inc"};

    @GET("/api/v1/quote")
    Observable<SharesItem> getShare(
            @Query("symbol") String token,
            @Query("token") String name
    );
}
