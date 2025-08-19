public class User {
    private String username;
    private String password;
    private byte[] photo;

    public User() {}

    public User(String username, String password, byte[] photo) {
        this.username = username;
        this.password = password;
        this.photo = photo;
    }

    public String getUsername() { return username; }
    public String getPassword() { return password; }
    public byte[] getPhoto() { return photo; }

    public void setUsername(String username) { this.username = username; }
    public void setPassword(String password) { this.password = password; }
    public void setPhoto(byte[] photo) { this.photo = photo; }
}
