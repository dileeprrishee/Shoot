package com.example.Shoot.Pojo.Generate;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GenerateOrderResponse {

@SerializedName("status")
@Expose
private String status;
@SerializedName("result")
@Expose
private OrderResult orderResult;

public String getStatus() {
return status;
}

public void setStatus(String status) {
this.status = status;
}

public OrderResult getOrderResult() {
return orderResult;
}

public void setOrderResult(OrderResult orderResult) {
this.orderResult = orderResult;
}

}