package matyk.webtasker;

public class PrivateAuth {
    public String passwdHash;
    public String passwdSalt;

    public PrivateAuth(String passwdHash, String passwdSalt) {
        this.passwdHash = passwdHash;
        this.passwdSalt = passwdSalt;
    }
}
