package robotlib;
import lib.AuthenticationLibrary;

import org.apache.axis2.AxisFault;
import org.apache.axis2.client.Options;
import org.apache.axis2.client.ServiceClient;
import org.testng.Assert;
import org.wso2.carbon.admin.mgt.stub.AdminManagementServiceStub;
import org.wso2.carbon.admin.mgt.stub.beans.xsd.AdminMgtInfoBean;
import org.wso2.carbon.admin.mgt.stub.beans.xsd.CaptchaInfoBean;


public class AdminManagementServiceLibrary{
	public static final String ROBOT_LIBRARY_SCOPE = "GLOBAL";

	private AdminManagementServiceStub stub;


	private boolean updatePasswordWithUserInput;
	private CaptchaInfoBean generateRandomCaptcha;
	private boolean initiatePasswordReset;

	//Constructor		
	public AdminManagementServiceLibrary(){

	}		

	public void  AssertupdatePasswordWithUserInput(boolean expected) {	
		Assert.assertEquals(expected, updatePasswordWithUserInput);		
	}
	public boolean updatePasswordWithUserInput(AdminMgtInfoBean adminInfoBean,CaptchaInfoBean captchaInfoBean,String confirmationKey)  throws java.lang.Exception{
		this.updatePasswordWithUserInput= stub.updatePasswordWithUserInput(adminInfoBean,captchaInfoBean,confirmationKey);
		return this.updatePasswordWithUserInput;
	}
	public void  AssertgenerateRandomCaptcha(CaptchaInfoBean expected) {	
		Assert.assertEquals(expected, generateRandomCaptcha);		
	}
	public CaptchaInfoBean generateRandomCaptcha()  throws java.lang.Exception{
		this.generateRandomCaptcha= stub.generateRandomCaptcha();
		return this.generateRandomCaptcha;
	}
	public void  AssertinitiatePasswordReset(boolean expected) {	
		Assert.assertEquals(expected, initiatePasswordReset);		
	}
	public boolean initiatePasswordReset(AdminMgtInfoBean adminInfoBean,CaptchaInfoBean captchaInfoBean)  throws java.lang.Exception{
		this.initiatePasswordReset= stub.initiatePasswordReset(adminInfoBean,captchaInfoBean);
		return this.initiatePasswordReset;
	}


	public static void main(String[] args) {
		//AdminManagementServiceLibrary l=new AdminManagementServiceLibrary();
		//l.setX(10);
		//l.setY(25);
		//System.out.println(l.getSum());
	}

	public void initAdminManagementService() throws AxisFault {
		String sessionCookie=AuthenticationLibrary.sessionString;
		String serviceName = "AdminManagementService";
		String endPoint;
		String backEndUrl = "https://localhost:9443/services/";
		endPoint = backEndUrl + "/services/" + serviceName;
		stub = new AdminManagementServiceStub(endPoint);
		// Authenticate Your stub from sessionCooke
		ServiceClient serviceClient;
		Options option;

		serviceClient = stub._getServiceClient();
		option = serviceClient.getOptions();
		option.setManageSession(true);
		option.setProperty(org.apache.axis2.transport.http.HTTPConstants.COOKIE_STRING,sessionCookie);
	}

}