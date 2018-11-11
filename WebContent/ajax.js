/**
 * aaaaaaaajax
 */

function RssAction(dataArray){
	var req_=$.ajax({            
		type: "GET",
		url: "/PushServ",
		dataType: "json",
		traditional: true,
		async:false,
		data: {
			array: dataArray
		}
	});
	return req_;
}

function RssGetWeb(){
	var dataArray=new Array();
	dataArray.push(1);
	dataArray.push(url.value);
	dataArray.push(encode.value);
	var ret=RssAction(dataArray).responseText;
	var jsonR=JSON.parse(ret);
	raw_data.innerHTML="<xmp>"+jsonR[0]+"</xmp>";
	feed_title.value=jsonR[1];
	feed_link.value=url.value;
	feed_desr.value=jsonR[2];
}

function RssExtract(){
	var dataArray=new Array();
	dataArray.push(2);
	dataArray.push(global_regex.value);
	dataArray.push(item_regex.value);
	var ret=RssAction(dataArray).responseText;
	var jsonR=JSON.parse(ret);
	extract_data.innerHTML="<xmp>"+jsonR[0]+"</xmp>";
}

function RssGenerate(){
	var dataArray=new Array();
	dataArray.push(3);
	dataArray.push(feed_title.value);
	dataArray.push(feed_link.value);
	dataArray.push(feed_desr.value);
	
	dataArray.push(item_title.value);
	dataArray.push(item_link.value);
	dataArray.push(item_desr.value);
	
	var ret=RssAction(dataArray).responseText;
	var jsonR=JSON.parse(ret);
	generate_data.innerHTML="<xmp>"+jsonR[0]+"</xmp>";
}
