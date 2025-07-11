package ru.netology.springBootDemo.model;

import javax.validation.constraints.NotBlank;

public class UserRequest {
        @NotBlank(message = "User cannot be empty")
        private String user;

        @NotBlank(message = "Password cannot be empty")
        private String password;

        public String getUser() {
                return user;
        }

        public void setUser(String user) {
                this.user = user;
        }

        public String getPassword() {
                return password;
        }

        public void setPassword(String password) {
                this.password = password;
        }
}