package dto;

public class ResponseDTO{
	private int status;
	private Object body;

	//constructor
	public ResponseDTO(int status,Object body){
		this.status=status;
		this.body=body;
	}

	//setter
	public void setStatus(int status){
		this.status=status;
	}

	public void setBody(Object body){
		this.body=body;
	}

	//getter
	public int getStatus(){
		return status;
	}

	public Object getBody(){
		return body;
	}

}
