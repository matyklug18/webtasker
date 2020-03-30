package matyk.webtasker;

public class UserInfo {
    public PrivateAuth auth;
    public boolean isAdmin;

    public UserInfo(PrivateAuth auth, boolean isAdmin) {
        this.auth = auth;
        this.isAdmin = isAdmin;
    }
}
