package Command;

public class ShowBalanceOff implements Command {
	
	Balance balance;

	public ShowBalanceOff(Balance balance) {
		this.balance=balance;
	}
	
	@Override
	public void execute() {
		// TODO Auto-generated method stub
		balance.switchOff();
	}

}
