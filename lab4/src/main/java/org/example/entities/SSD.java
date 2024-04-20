package org.example.entities;

import javax.persistence.*;

@Entity
@Table(name = "ssd")
public class SSD {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "dataStorage_id")
    private DataStorage dataStorage;

    @Column(name = "cost")
    private int cost;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "buyer_id")
    private Buyer buyer;

    public SSD(DataStorage dataStorage, int cost, Buyer buyer) {
        this.dataStorage = dataStorage;
        this.cost = cost;
        this.buyer = buyer;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public DataStorage getDataStorage() {
        return dataStorage;
    }

    public void setDataStorage(DataStorage dataStorage) {
        this.dataStorage = dataStorage;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public Buyer getBuyer() {
        return buyer;
    }

    public void setBuyer(Buyer buyer) {
        this.buyer = buyer;
    }

    @Override
    public String toString() {
        return "SSD {" +
                "id =" + id +
                ", dataStorage =" + dataStorage +
                ", cost ='" + cost + '\'' +
                ", buyer =" + buyer +
                '}';
    }
}
