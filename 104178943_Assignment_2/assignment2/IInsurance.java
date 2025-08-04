package assignment2;

public interface IInsurance {
    double getPriceInsuarance(int numberClient);
    void createInsuarance(ATour tour, boolean isCreateInsurance) throws InsuranceException;
}
