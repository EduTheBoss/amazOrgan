package amazOrgan.ifaces;

import amazOrgan.pojos.Receptor;

public interface ReceptorManager {
	// Insert a Receptor
	public void addReceptor (Receptor r);
	// Update alive
	public void updateAlive (Integer DNI); 
	//Update status
	public void updateStatus (Integer DNI ); 
	//Update Urgency
	public void updateUrgency (Integer DNI);
	//Select Receptor
	public void getReceptor (Integer DNI);
	// Show by bloodtype
	public void showReceptorsByBloodType (String bloodtype);
	//Show by urgency
	public void showReceptorsByUrgency();
}
