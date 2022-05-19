package Command;

public class ShowBalanceOn implements Command {

	Balance balance;

	public ShowBalanceOn(Balance balance) {
		this.balance=balance;
	}
	
	@Override
	public void execute() {
		// TODO Auto-generated method stub
		balance.switchOn();
	}

}
