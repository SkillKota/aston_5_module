package homework5;

import java.util.Objects;

public class User {
    private int id;
    private String name;
    private String password;
    private String email;

    private User(BuilderUser builder){
        this.id = builder.id;
        this.name = builder.name;
        this.password = builder.password;
        this.email = builder.email;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public static class BuilderUser{
        private int id;
        private String name;
        private String password;
        private String email;

        public BuilderUser(int id) {
            this.id = id;
        }

        public BuilderUser name(String name){
            this.name = name;
            return this;
        }

        public BuilderUser password(String password){
            this.password = password;
            return this;
        }

        public BuilderUser email(String email){
            this.email = email;
            return this;
        }

        public User build(){
            validate();
            return new User(this);
        }
        private void validate(){
            if(id <= 0){
                throw new IllegalArgumentException("ID не может быть меньше нуля");
            }

            if(name == null || name.trim().length() < 1){
                throw new IllegalArgumentException("Имя должно быть больше 1 символа");
            }

            if(password == null || password.length() < 6){
                throw new IllegalArgumentException("Пароль должен быть больше 6 символов");
            }

            if (email == null || !email.contains("@") || !email.contains(".")){
                throw new IllegalArgumentException("Неправильный формат email");
            }

        }
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id == user.id && Objects.equals(name, user.name) && Objects.equals(password, user.password) && Objects.equals(email, user.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, password, email);
    }
}
