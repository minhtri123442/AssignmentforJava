package Model;

public class CharityFeePolicy implements LateFeePolicy {
    @Override
    public double applyPolicy(double baseFee) {
        return baseFee * 0.5;
    }

    @Override
    public String getPolicyName() {
        return "Thang tu thien — giam 50%";
    }
}