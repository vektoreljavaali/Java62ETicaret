package com.vektorel.model;

public class UserProfile {
   public String gender;
   public Name name;
   public Location location;
   public String email;
   public Login login;
   public Dob dob;
   public String phone;
   public Picture picture;

    public static class Name{
      public   String title;
      public String first;
      public   String last;
    }
    public static class Location{
        public Street street;
        public String city;
        public String country;
        public String state;
        public String postcode;
        public Coordinates coordinates;

        public static class Street{
            public String name;
            public int number;
        }
        public static class Coordinates{
            public String latitude;
            public String longitude;
        }
    }
    public static class Login{
        public String uuid;
        public String username;
        public String password;
        public String salt;

    }
    public static class Dob{
        public String date;
        public int age;
    }
    public static class Picture{
        public String large;
        public String medium;
        public String thumbnail;

    }
    public UserProfile(){

    }
}
