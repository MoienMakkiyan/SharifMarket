public class CostumerPanel {
    public static void main(String[] args) {
        CostumerMannager costumerMannager = new CostumerMannager();
        CostumerInput costumerInput = new CostumerInput(costumerMannager);
        costumerInput.run();
    }
}

