package ds.kontrolnaya.models;

import java.io.Serializable;
import java.util.Objects;

public class Fee implements Serializable {

    enum FeeType{
        BUSINESS,
        COMMUNAL,
        TAX;

        public static FeeType convert(int type){
            if(type==1) return BUSINESS;
            if(type==2) return COMMUNAL;
            return TAX;
        }
    }
    private String id;
    private FeeType type;
    private String title;
    private String payer;
    private Double sum;

    public Fee(int type, String title, String payer, Double sum) {
        this.type = FeeType.convert(type);
        this.title = title;
        this.payer = payer;
        this.sum = sum;
    }

    public Fee(String id, int type, String title, String payer, Double sum) {
        this.id = id;
        this.type = FeeType.convert(type);
        this.title = title;
        this.payer = payer;
        this.sum = sum;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public FeeType getType() {
        return type;
    }

    public void setType(FeeType type) {
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPayer() {
        return payer;
    }

    public void setPayer(String payer) {
        this.payer = payer;
    }

    public Double getSum() {
        return sum;
    }

    public void setSum(Double sum) {
        this.sum = sum;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Fee fee = (Fee) o;
        return id == fee.id &&
                type == fee.type &&
                Objects.equals(title, fee.title) &&
                Objects.equals(payer, fee.payer) &&
                Objects.equals(sum, fee.sum);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, type, title, payer, sum);
    }
}
