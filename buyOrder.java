public class buyOrder extends Order{
    public buyOrder(long id, long ts, int tId, int sId, int p, int q) {
        super(id, ts, tId, sId, p, q);

    }

    @Override
    public boolean isSell() {

        return false;
    }

    @Override
    public int compareTo(Order o) {
        if (this.getPrice() == o.getPrice()) {
            return (int) (this.getId() - o.getId());
        }

        return o.getPrice() - this.getPrice();
    }
}
