package beans;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Customer extends User{

    public class CreditCard{
        private String number;
        private Date validity;
        private String code;
        private Double balance;

        public CreditCard(String number, Date validity, String code, Double balance) {
            this.number = number;
            this.validity = validity;
            this.code = code;
            this.balance = balance;
        }

        public String getNumber() {
            return number;
        }

        public void setNumber(String number) {
            this.number = number;
        }

        public Date getValidity() {
            return validity;
        }

        public void setValidity(Date validity) {
            this.validity = validity;
        }

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public Double getBalance() {
            return balance;
        }

        public void setBalance(Double balance) {
            this.balance = balance;
        }
    }

    public class Account{
        private String username;
        private String password;

        public Account(String username, String password) {
            this.username = username;
            this.password = password;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }
    }

    private CreditCard creditCard;
    private Account account;
    private String comment;
    private Integer rank;
    private List<Request> requests = new ArrayList<>();
    private List<Handyman> favoriteHandymen = new ArrayList<>();

    public Customer(String name, String surname, String phone, String email,
                    String username, String password, String comment){
        super(name, surname, phone, email);
        this.account = new Account(username, password);
        this.comment = comment;
        this.rank = 1;
    }

    public CreditCard getCreditCard() {
        return creditCard;
    }

    public void setCreditCard(CreditCard creditCard) {
        this.creditCard = creditCard;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Integer getRank() {
        return rank;
    }

    public void setRank(Integer rank) {
        this.rank = rank;
    }

    public void addRequestArchive(Request request){
        requests.add(0, request);
    }

    public List<Request> getRequests() {
        return requests;
    }

    public List<Handyman> getFavoriteHandymen() {
        return favoriteHandymen;
    }

    public void addFavoriteHandyman(Handyman handyman){
        favoriteHandymen.add(handyman);
    }
}
