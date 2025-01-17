package amazOrgan.jdbc;

import java.sql.PreparedStatement;
import java.util.List;

import amazOrgan.ifaces.DoctorManager;
import amazOrgan.ifaces.DonorManager;
import amazOrgan.pojos.Donor;
/*addDonor (d: Donor): void 

deleteDonor(DNI: int): void 

getDonor( DNI: int): void  

updateAlive (DNI: int): void 

showDonorsByBloodType (bloodtype: text): void 
*/



public class JDBCDonorManager implements DonorManager {
	
		private JDBCManager manager;
		
		public JDBCDonorManager(JDBCManager m) {
			this.manager = m;
		}

		@Override
		public void addDonor(Donor d) {
			
			
			// TODO Auto-generated method stub el id es autoincrement
			
		}

		@Override
		public void deleteDonor(Integer DNI) {
			try {
				String sql = "DELETE FROM donor WHERE DNI=?";
				
				PreparedStatement prep = manager.getConnection().prepareStatement(sql);
				prep.setInt(1, DNI);
				prep.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		

		@Override
		public void getDonor(Integer DNI) {
			// TODO Auto-generated method stub
			
			

			
		}

		@Override
		public void updateAlive(Integer DNI) {
			
			
			// TODO Auto-generated method stub
			
		}

		@Override
		public List<Donor> showDonorsByBloodType(String bloodType) {
			// TODO Auto-generated method stub
			return null;
		}

}
