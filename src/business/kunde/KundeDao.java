package business.kunde;

import java.util.List;

public class KundeDao {
    private static KundeDao instance;

    public List<KundeEntity> getAllKunden() {
        return null;
    }

    private KundeDao() {

    }

    public static KundeDao getInstance() {
        if (instance == null) {
            instance = new KundeDao();
        }
        return instance;
    }
}
