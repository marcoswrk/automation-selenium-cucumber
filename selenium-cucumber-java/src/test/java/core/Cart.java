package core;

public class Cart {
    private String imageSrc;
    private String description;
    private String price;
    private String quantity;
    private String total;

    public Cart() {
    }

    public Cart(String imageSrc, String description, String price, String quantity, String total) {
        this.imageSrc = imageSrc;
        this.description = description;
        this.price = price;
        this.quantity = quantity;
        this.total = total;
    }

    public String getImageSrc() {
        return imageSrc;
    }

    public void setImageSrc(String imageSrc) {
        this.imageSrc = imageSrc;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }
}
