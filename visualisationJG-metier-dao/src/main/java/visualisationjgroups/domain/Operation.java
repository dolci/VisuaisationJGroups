package visualisationjgroups.domain;

import java.util.ArrayList;

/**
 * opération de Mbean
 * 
 *
 */
public class Operation {
	// name of method
	private String nameOp;
	// return type of method
	private String returnType;
	//description method 
	private String description;
	// list of parameters
	private ArrayList params  = new ArrayList();
	// signature of method
	private String signature;
	
	//-----------------constructeur
	public Operation(String nameOp, String returnType, String description,ArrayList params) {
		
		this.nameOp = nameOp;
		this.returnType = returnType;
		this.description = description;
		this.params = params;
		if(params.size()>0)
		this.signature = returnType+" "+nameOp+"("+params.toString()+")";
		else 
			this.signature = returnType+" "+nameOp+"( )";
	}
	//---------------getters & setters
	public String getReturnType() {
		return returnType;
	}
	public void setReturnType(String returnType) {
		this.returnType = returnType;
	}
	public String getNameOp() {
		return nameOp;
	}
	public void setNameOp(String nameOp) {
		this.nameOp = nameOp;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getSignature() {
		return signature;
	}
	public void setSignature(String signature) {
		this.signature = signature;
	}
	public ArrayList getParams() {
		return params;
	}
	public void setParams(ArrayList params) {
		this.params = params;
	}
	
	

}
