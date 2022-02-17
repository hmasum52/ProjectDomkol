package team.memoryleak.domkol.dagger.module;



import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import team.memoryleak.domkol.dagger.anotation.AppScope;

@Module
public class NetworkModule {
    // firebase section ===================================================

    // retrofit section ===================================================
    @Provides
    @AppScope
    static Api provideNasaSSCApi() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.level(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .followRedirects(true)
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("")
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        return new Api(retrofit);
    }


    public static class Api {
        private final Retrofit retrofit;

        public Api(Retrofit retrofit) {
            this.retrofit = retrofit;
        }

        public Retrofit getRetrofit() {
            return retrofit;
        }

        public <T> T create(Class<T> tClass) {
            return retrofit.create(tClass);
        }
    }

}
