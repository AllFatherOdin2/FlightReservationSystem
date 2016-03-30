/**
 * 
 */
package CS509.client.airplane;

/**
 * @author David
 *
 */
public class Airplane {
	private String mManufacturer;
	private String mModel;
	private int	mFirstClassSeats;
	private int mCoachSeats;
	
	/**
	 * Basic constructor for Airplane
	 */
	public Airplane(){
		mManufacturer = "";
		mModel = "";
		mFirstClassSeats = 0;
		mCoachSeats = 0;
	}
	
	/**
	 * Constructor that takes attributes for Airplane
	 */
	public Airplane(String manu, String model, int fcSeats, int cSeats){
		mManufacturer = manu;
		mModel = model;
		mFirstClassSeats = fcSeats;
		mCoachSeats = cSeats;
	}
	
	public boolean isValid(){
		if(mManufacturer == null || mModel == null){
			return false;
		} else if (mFirstClassSeats <= 0 || mCoachSeats <= 0){
			return false;
		} else {
			return true;
		}
	}

	/**
	 * @return the mManufacturer
	 */
	public String getmManufacturer() {
		return mManufacturer;
	}

	/**
	 * @param mManufacturer the mManufacturer to set
	 */
	public void setmManufacturer(String mManufacturer) {
		this.mManufacturer = mManufacturer;
	}

	/**
	 * @return the mModel
	 */
	public String getmModel() {
		return mModel;
	}

	/**
	 * @param mModel the mModel to set
	 */
	public void setmModel(String mModel) {
		this.mModel = mModel;
	}

	/**
	 * @return the mFirstClassSeats
	 */
	public int getmFirstClassSeats() {
		return mFirstClassSeats;
	}

	/**
	 * @param mFirstClassSeats the mFirstClassSeats to set
	 */
	public void setmFirstClassSeats(int mFirstClassSeats) {
		this.mFirstClassSeats = mFirstClassSeats;
	}

	/**
	 * @return the mCoachSeats
	 */
	public int getmCoachSeats() {
		return mCoachSeats;
	}

	/**
	 * @param mCoachSeats the mCoachSeats to set
	 */
	public void setmCoachSeats(int mCoachSeats) {
		this.mCoachSeats = mCoachSeats;
	}
	
}
