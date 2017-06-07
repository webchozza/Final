function viewTags(str) {
	var sep = str.split(",");
	$("#tags").append("<i class='icon fa-tags'></i>");
	for (i=0; i<sep.length; i++) {
		if(i==(sep.length-1)){
			$("#tags").append('<a href=taglist.do?tag='+urlencode(sep[i])+'&sort=>'+sep[i]+'</a>');
		}else{
			$("#tags").append('<a href=taglist.do?tag='+urlencode(sep[i])+'&sort=>'+sep[i]+'</a>, ');
		}
	}
}

function urlencode(str) {
    str = (str + '').toString();
    return encodeURIComponent(str)
        .replace(/!/g, '%21')
        .replace(/'/g, '%27')
        .replace(/\(/g, '%28')
        .replace(/\)/g, '%29')
        .replace(/\*/g, '%2A')
        .replace(/%20/g, '+');
}