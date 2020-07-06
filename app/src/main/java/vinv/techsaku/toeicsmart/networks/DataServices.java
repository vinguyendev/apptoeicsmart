package vinv.techsaku.toeicsmart.networks;

public class DataServices {

    public static APIServices getAPIService() {
        return RetrofitClient.getClient().create(APIServices.class);
    }
}
