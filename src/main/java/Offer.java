public class Offer {
    long id;
    String name;
    double price;
    double oldPrice;
    boolean isFreeDelivery = true;
    double deliveryprice;
    String state;
    int dicountPercentage;
    String category;

    public Offer(long id, String name, double price, double oldPrice, String category) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.oldPrice = oldPrice;
        this.category = category;

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getOldPrice() {
        return oldPrice;
    }

    public void setOldPrice(double oldPrice) {
        this.oldPrice = oldPrice;
    }

    public double getDeliveryprice() {
        return deliveryprice;
    }

    public void setDeliveryprice(double deliveryprice) {
        this.isFreeDelivery = false;
        this.deliveryprice = deliveryprice;
    }

    public int getDicountPercentage() {
        return dicountPercentage;
    }

    public void setDicountPercentage(int dicountPercentage) {
        this.dicountPercentage = dicountPercentage;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
