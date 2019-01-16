//jqGrid的配置信息
try{
	$.jgrid.defaults.width = 1000;
$.jgrid.defaults.responsive = true;
$.jgrid.defaults.styleUI = 'Bootstrap';
}catch(error){
	
}


//工具集合Tools
window.T = {};

// 获取请求参数
// 使用示例
// location.href = http://localhost:8080/index.html?id=123
// T.p('id') --> 123;
var url = function(name) {
	var reg = new RegExp("(^|&)"+ name +"=([^&]*)(&|$)");
	var r = window.location.search.substr(1).match(reg);
	if(r!=null)return  unescape(r[2]); return null;
};
T.p = url;

//全局配置
$.ajaxSetup({
	dataType: "json",
	contentType: "application/json",
	cache: false
});

function hasPermission(permission) {
	try{
		if (window.parent.permissions.indexOf(permission) > -1) {
        return true;
    } else {
        return false;
    }
	}
	catch(e){
		if (localStorage.permissions.indexOf(permission) > -1) {
	        return true;
	    } else {
	        return false;
	    }
	}
    
}

// jqGrid 重置表格宽度
function setWidth(id) {
	$("#" + id + "")[0].contentWindow.setWidth();
}

//console.log( window.parent);
//重写alert
window.alert = function(msg, callback){
	parent.layer.alert(msg, function(index){
		parent.layer.close(index);
		if(typeof(callback) === "function"){
			callback("ok");
		}
	});
}


//重写confirm式样框
window.confirm = function(msg, callback){
	parent.layer.confirm(msg, {btn: ['确定','取消']},
	function(){//确定事件
		if(typeof(callback) === "function"){
			callback("ok");
		}
	});
}

//选择一条记录
function getSelectedRow() {
    var grid = $("#jqGrid");
    var rowKey = grid.getGridParam("selrow");
    if(!rowKey){
    	alert("请选择一条记录");
    	return ;
    }
    
    var selectedIDs = grid.getGridParam("selarrrow");
    if(selectedIDs.length > 1){
    	alert("只能选择一条记录");
    	return ;
    }
    
    return selectedIDs[0];
}

//选择多条记录
function getSelectedRows() {
    var grid = $("#jqGrid");
    var rowKey = grid.getGridParam("selrow");
    if(!rowKey){
    	alert("请选择一条记录");
    	return ;
    }
    
    return grid.getGridParam("selarrrow");
}

function Vaild(value){
	this.value = value;
	this.isVerify = function (value) {
        if (value instanceof Array) {
            for (var j = 0, len = value.length; j < len; j++) {
                for (var i in value[j]) {
                    if (value[j].require) { 
                        if (this.isnull({hint: value[j].name, val: value[j].val})) {
                            return true;
                        }
                    }
                    if (value[j].email) {
                        if (this.isEmail({hint: value[j].name, val: value[j].val})) {
                            return true;
                        }
                    }
                    if (value[j].mobile) {
                        if (this.isMobile({hint: value[j].name, val: value[j].val})) {
                            return true;
                        }
                    }
                    if (value[j].number) {
                        if (this.number({hint: value[j].name, val: value[j].val})) {
                            return true;
                        }
                    }
                    if (value[j].numberSmall2) {
                        if (this.numberTwo({hint: value[j].name, val: value[j].val})) {
                            return true;
                        }
                    }
                    if (value[j].numberSmall3) {
                        if (this.millesimal({hint: value[j].name, val: value[j].val})) {
                            return true;
                        }
                    }
                    if (value[j].isChinese) {
                        if (this.isChinese({hint: value[j].name, val: value[j].val})) {
                            return true;
                        }
                    }

                }
            }
        }
    }
};
Vaild.prototype = {
		phone : function(){
			if(!(/^(13[0-9]|15([0-3]|[5-9])|17[2,3,4,5,6,8,9]|18[0-9])\d{8}$/.test(this.value.phone))){
				alert('请输入正常的手机号码');
			   return true;
			}else{
				return false;
			}
			
		},
		email : function(){
			if(!(/[\w!#$%&'*+/=?^_`{|}~-]+(?:\.[\w!#$%&'*+/=?^_`{|}~-]+)*@(?:[\w](?:[\w-]*[\w])?\.)+[\w](?:[\w-]*[\w])?/.test(this.value.email))){
				alert('请输入正确的邮箱');
				return true;
			}else{
				return false;
			};
		},
		isnull:function(par){
			if (par.val === '' || par.val == null){
				alert(par.hint+'不能为空');
				return true;
			} else {
				return false;
			}
		},
		empty:function(obj){
			if((/\s/g.test(obj.val))){
				alert(obj.hint+'不能出现空字符')
				return true;
			}else{
				return false;
			}
		},
		numberTwo:function(obj){
			if(obj.val !== "" && /^([1-9]\d+||\d)(\.\d{1,2})?$/.test(obj.val)){
				return false;
			}else{
				alert(obj.hint+'输入数字不正确,小数点后面最多保留两位');
				return true;
			}
		},
		millesimal:function(obj){
			if(obj.val !== "" && /^([1-9]\d+||\d)(\.\d{1,3})?$/.test(obj.val)){
				return false;
			}else{
				alert(obj.hint+'输入的数字不正确,小数点后最多保留三位');
				return true;
			}
		},
		lessTen:function(num){
			console.log(num)
				if(num > 1000000){
                     alert('输入范围0.00-999999.99'); 
                    return true;         
				}else{
					return false;
				}			
		},
		lesshundred:function(num){
			console.log(num)
				if(num >= 100){
                     alert('输入范围0.00-99.99'); 
                    return true;         
				}else{
					return false;
				}			
		},
		numberTwo2:function(num){
			if(!(/^([1-9]\d+||\d)(\.\d{1,2})?$/.test(num))){
				alert('请输入正确的数字,且小数点后面保留两位小数');
				return true;
			}else{
				return false;
			}
		},
		numberTwo3:function(num){
			if(!(/^([1-9]\d+||\d)(\.\d{1,3})?$/.test(num))){
				alert('请输入正确的数字,且小数点后面保留三位小数');
				return true;
			}else{
				return false;
			}
		},
		emptyNo:function(obj){
			console.log(obj[2].value)
			for(var i = 0, len = obj.length; i < len ; i++){
				if(obj[i].value == '产品简介' || obj[i].value == null){
					alert(obj[i].text+'不能为空');
					return true;
				}else{
					return false;
				}
			}
		},
		number:function(obj){
			if(/^[1-9]\d*$/.test(obj.val)){
				return false;
			}else{
				alert(obj.hint+'需要输入正整数');
				return true;
			}
		},
		scienceComputed:function(obj){
			if(!(/^(\+?|-?)(([1-9]\d+)|[0-9])(\.\d+)?((E|e)(\+?|-?)\d+)?$/.test(obj.val))){
				alert(obj.hint+',输入的数字不正确');
				return true;
			}else{
				return false;
			}
		},
		isMobile : function(obj){
            if((/^(13[0-9]|15([0-3]|[5-9])|17[2,3,4,5,6,8,9]|18[0-9])\d{8}$/.test(obj.val))){
                return false;

            }else{
                alert(obj.hint+'输入手机的号码不正确');
                return true;
            }

        },
        isEmail : function(obj){
			if((/[\w!#$%&'*+/=?^_`{|}~-]+(?:\.[\w!#$%&'*+/=?^_`{|}~-]+)*@(?:[\w](?:[\w-]*[\w])?\.)+[\w](?:[\w-]*[\w])?/.test(obj.val))){
				return false;
			}else{
				alert(obj.hint+'输入邮箱不正确');
				return true;
			};
		},
		isChinese:function(obj){
			if((/[\u4e00-\u9fa5]/.test(obj.val))){
					alert(obj.hint+'不能输入中文字符');
					return true;
			}else{
				return false;
			};
		}
		
}
function TimeControl(){
   this.today = function() {
	    var today=new Date();
	    var h=today.getFullYear();
	    var m=today.getMonth()+1;
	    var d=today.getDate();
	    m= m<10?"0"+m:m;   //  这里判断月份是否<10,如果是在月份前面加'0'
	    d= d<10?"0"+d:d;   //  这里判断日期是否<10,如果是在日期前面加'0'
	    return h+"-"+m+"-"+d;
	};
	this.today2 = function (){
	    var today=new Date(new Date().getTime()-86400000*6);
	    var h=today.getFullYear();
	    var m=today.getMonth()+1;
	    var d=today.getDate();
	    m= m<10?"0"+m:m;   //  这里判断月份是否<10,如果是在月份前面加'0'
	    d= d<10?"0"+d:d;   //  这里判断日期是否<10,如果是在日期前面加'0'
	    return h+"-"+m+"-"+d;
	};
	this.add = function(m){
		return m<10?'0'+m:m
	};
	this.formatDate= function(needTime){  
	     //needTime是整数，否则要parseInt转换  
	       var time = new Date(needTime);  
	       var y = time.getFullYear();  
	       var m = time.getMonth()+1;  
	       var d = time.getDate();  
	       var h = time.getHours();  
	      var mm = time.getMinutes();  
	      var s = time.getSeconds();  
	      return y+'-'+this.add(m)+'-'+this.add(d)+' '+this.add(h)+':'+this.add(mm)+':'+this.add(s);  
	  } 
};
function Grid(obj,list,colNames){
	this.obj = obj;
	this.list = list;
	this.colNames = colNames;
};
Grid.prototype = {
		fun :function(){
			var that = this;
			  $(this.obj.gridName).jqGrid({
			        url: this.obj.url,
			        datatype: "json",
			        colModel: this.list,
					viewrecords: true,
			        height: 400,
			        rowNum: 10,
					rowList : [10,30,50],
			        rownumbers: true, 
			        rownumWidth: 55, 
			        autowidth:true,
			       shrinkToFit:this.obj.judgeScroll,
//			       autoScroll: false,
			       colNames:this.colNames,
			        pager: this.obj.pageName,
			        jsonReader : {
			            root: "page.list",
			            page: "page.currPage",
			            total: "page.totalPage",
			            records: "page.totalCount"
			        },
			        prmNames : {
			            page:"page", 
			            rows:"limit", 
			            order: "order",
			        },
			        loadComplete:function(){
			           $(that.obj.gridName).jqGrid('setLabel',0,'序号',{  
			         	        'text-align' : 'center',
			         	}); 
			           that.obj.judgeScroll == true? $(that.obj.gridName).closest(".ui-jqgrid-bdiv").css({ "overflow-x" : "hidden" }) : "";
			           if (that.obj.newData === true){
			        	   console.log('开始')
			        	   $(that.obj.gridName).jqGrid('clearGridData');  //清空表格
			        	   $(that.obj.gridName).jqGrid('setGridParam',{  // 重新加载数据
			        	         datatype:'json',
//			        	         postData:param,    //参数
			        	         url:'../../controlRule/ruleInfo',  //  newdata 是符合格式要求的需要重新加载的数据 
			        	         page:1
			        	   }).trigger("reloadGrid");
			           }
			        	var totalCount = $(that.obj.gridName).getGridParam('records');
			        	if(totalCount <=0){
			        		$(that.obj.pageName+"_center").hide();
			        	}else{
			        		$(that.obj.pageName+"_center").show();
			        	}
			        	var data = $(that.obj.gridName).jqGrid('getRowData');  
			        	console.log(data)
			        },
			        gridComplete:function(){
			        }
			    });
		},
		initWidth:function(){
			$(this.obj.gridName).setGridWidth(this.obj.width)
		},
		setGridParam:function(poptData){
			if(poptData.startTime!="" && poptData.endTime!=""){
				$(this.obj.gridName).jqGrid('setGridParam',{ 
					postData:poptData,
	                page:1
	            }).trigger("reloadGrid");
			}else{
				alert("时间条件不能为空");
			}
		},
		updateGrid:function(poptData){
			$(this.obj.gridName).jqGrid('setGridParam',{ 
				postData:poptData,
                page:1
            }).trigger("reloadGrid");
		}
};
function SetGrid(param,data,grid){
	 this.param = param;
	 this.data = data;
	 this.grid = grid;
	 this.total = function(){
		 var total;
		 total = (this.param.len % this.param.num == 0)? (this.param.len / this.param.num) : (Math.floor(this.param.len / this.param.num) +1);
		 return total;
	 };
	 this.initData = function(){
		 var newData = this.data.slice(this.param.startIndex,this.param.endIndex);
		 return newData;
	 };
	 this.initWidth = function(){
			$(this.grid.id).setGridWidth(this.grid.width)
		};
	 this.setgrid = function(){	
		 var that = this;
		$(this.grid.id).jqGrid({
			datatype: "local",
	        altRows: true,
	        colModel: this.grid.colModel,
	        pager: this.grid.pageId,
	        rowNum: this.grid.rowNum,
	        sortname: '0',
	        viewrecords: true,
	        rownumbers: true,
	        rownumWidth: 100,
	        gridview: true,
	        autowidth: true,
	        height: this.grid.height,
	        loadComplete: function () {
	            //更改表格头部显示序号
	            $(that.grid.id).jqGrid('setLabel', 0, '序号', {
	                'text-align': 'center', 'width': '100px'
	            });
	            $(that.grid.pageId+' .ui-pg-input').on('keydown',function(e){   //回车键
	            	if (e.keyCode == 13) {
	                    var numVal = $(this).val();
	                    if(isNaN(numVal)){
	                    	numVal = 1;
	                    };
	                    if (numVal < 1) {
	                    	numVal = 1;
//	                        that.param.index = numVal;
	                    } else if (numVal > that.total()) {
	                        numVal = that.total();
	                    }           
	                    that.param.index = numVal;
	                    that.param.endIndex = numVal * that.param.num;
	                    that.param.startIndex = that.param.endIndex - that.param.num;
	                    var newData = that.data.slice(that.param.startIndex, that.param.endIndex);
	                    var reader = {
	                        root: function (obj) {                    
	                            return newData;
	                        },
	                        page: function (obj) {
	                            return that.param.index;
	                        },
	                        total: function (obj) {
	                            return that.total();
	                        },
	                        records: function (obj) {
	                            return that.param.len;
	                        }
	                    };
	                    //data:that.data, 
	                    $(that.grid.id).setGridParam({data:that.data,localReader: reader}).trigger('reloadGrid');
	                }
	            });
	            $(that.grid.pageId+' .ui-pg-input').on(' blur', function (e) {  //失去焦点的时候
	                var e = e || window.event;
	                var numVal = $(this).val();
	                if(isNaN(numVal)){
	                	numVal = 1;
	                };
	                if (numVal < 1) {
	                    numVal = 1;
	                } else if (numVal > that.total()) {
	                    numVal = that.total();
	                }
	                that.param.index = numVal;
	                that.param.endIndex = numVal * that.param.num;
	                that.param.startIndex = that.param.endIndex - that.param.num;
	               var newData = that.data.slice(that.param.startIndex, that.param.endIndex);
	                var reader = {
	                    root: function (obj) {
	                        return newData;
	                    },
	                    page: function (obj) {
	                        return that.param.index;
	                    },
	                    total: function (obj) {
	                        return that.total();
	                    },
	                    records: function (obj) {
	                        return that.param.len;
	                    }
	                };
	                $(that.grid.id).setGridParam({data:that.data,localReader: reader}).trigger('reloadGrid');
	            });
	        }
		});
	////向后
		$('#next_'+that.grid.pageId.slice(1,that.grid.pageId.length)).on('click', function () {
		    if (that.param.index == that.total()) {
		    	that.param.index = that.total();
		    } else {
		    	that.param.index++;
		    	that.param.startIndex = that.param.endIndex;
		    	that.param.endIndex = that.param.num * that.param.index;
		        var newData = that.data.slice(that.param.startIndex, that.param.endIndex);
		        reader = {
		            root: function (obj) {
		                return newData;
		            },
		            page: function (obj) {
		                return that.param.index;
		            },
		            total: function (obj) {
		                return that.total();
		            },
		            records: function (obj) {
		                return that.param.len;
		            }
		        };
		        $(that.grid.id).setGridParam({data:that.data,localReader: reader}).trigger('reloadGrid');
		    }
		});
	////向前
		$('#prev_'+that.grid.pageId.slice(1,that.grid.pageId.length)).on('click', function () {
		    if (that.param.index == 1) {
		    	that.param.index = 1;
		    } else {
		    	that.param.index--;
		    	that.param.endIndex = that.param.startIndex;
		    	that.param.startIndex = that.param.endIndex - that.param.num;
		        var newData = that.data.slice(that.param.startIndex, that.param.endIndex);
		        reader = {
			            root: function (obj) {
			                return newData;
			            },
			            page: function (obj) {
			                return that.param.index;
			            },
			            total: function (obj) {
			                return that.total();
			            },
			            records: function (obj) {
			                return that.param.len;
			            }
			        };
			        $(that.grid.id).setGridParam({data:that.data,localReader: reader}).trigger('reloadGrid');
		    }
		});
	////最后
		$('#last_'+that.grid.pageId.slice(1,that.grid.pageId.length)).on('click', function () {
			that.param.index = that.total();
			that.param.endIndex = that.param.len;
			that.param.startIndex = that.param.num * (that.total() - 1);
		    var newData = that.data.slice(that.param.startIndex, that.param.endIndex);
		    reader = {
		            root: function (obj) {
		                return newData;
		            },
		            page: function (obj) {
		                return that.param.index;
		            },
		            total: function (obj) {
		                return that.total();
		            },
		            records: function (obj) {
		                return that.param.len;
		            }
		        };
		        $(that.grid.id).setGridParam({data: that.data, localReader: reader}).trigger('reloadGrid');
		});	
	////第一页
		$('#first_'+that.grid.pageId.slice(1,that.grid.pageId.length)).on('click', function () {
			that.param.index = 1;
			that.param.endIndex = that.param.num;
			that.param.startIndex = that.param.endIndex - that.param.num;
		   var newData =  that.data.slice(that.param.startIndex, that.param.endIndex);
		   reader = {
		            root: function (obj) {
		                return newData;
		            },
		            page: function (obj) {
		                return that.param.index;
		            },
		            total: function (obj) {
		                return that.total();
		            },
		            records: function (obj) {
		                return that.param.len;
		            }
		        };
		        $(that.grid.id).setGridParam({data: that.data, localReader: reader}).trigger('reloadGrid');
		});	
		
	 };
	 this.initGrid = function(){
		 var that = this;
		 var reader = {
				    root: function (obj) {
				        return that.initData();
				    },
				    page: function (index) {
				        return that.param.index;
				    },
				    total: function (obj) {
				        return that.total();
				    },
				    records: function (obj) {
				        return that.param.len;
				    }
				};
		 $(that.grid.id).setGridParam({data: that.data, localReader: reader}).trigger('reloadGrid');
	 };
	 this.clear = function(){
		 console.log($(this.grid.id));
//		 $(this.grid.pageId).empty();
		 console.log(this.param);console.log(this.data);console.log(this.grid);
		 $(this.grid.id).jqGrid('clearGridData'); 
	 }
	 
	
}
//导出时间范围
Date.prototype.timeScope = function(startDate,endDate){
    return (endDate.getTime()-startDate.getTime())/(24 * 60 * 60 * 1000);
};

function UploadIcon(obj){
//	this.closeBtn = obj.close;
	var uploader;
	this.create=function(par,callback){
		var that = this;
		 uploader = WebUploader.create({
		    auto: par.auto,
		    fileNumLimit: par.num,
//		    swf: './libs/Uploader.swf',
		    fileSingleSizeLimit: par.size,
		    formData :par.formData,
		    server: par.url,
		    pick: par.id,
		    resize: par.resize,  
		    accept: par.accept,
		});
		this.newUploader = uploader;
		uploader.on( 'fileQueued', function( file ) {
		    var $li = $(
		        '<div id="' + file.id + '" class="file-item thumbnail">' +
		        '<img>' +
		        '<div class="info">' + file.name + '</div>' +
		        '<i class="removeIcon glyphicon glyphicon-remove"></i>'+
		        '</div>'
		        ),
		        $img = $li.find('img');
		    $(par.iconWrap).append( $li);
		    that.dom = $li;
		    uploader.makeThumb( file, function( error, src ) {
		        if ( error ) {
		            $img.replaceWith('<span>不能预览</span>');
		            return;
		        }
		        $img.attr( 'src', src );
		    }, par.Wsize, par.Hsize );
		    $li.on( 'click', 'i', function() {
		    	 $li.remove()
		        uploader.removeFile(file);
		    	 that.removeIndex = false;
//		    	 that.removeIcon(file)
		    });
		    $(par.closeBtn).on('click',function(){
		    	console.log(par.closeBtn)
		    	  $li.remove()
		    	  uploader.removeFile(file);
		    	 that.removeIndex = false;
		    });
		    that.removeFile = function (){
				   $li.remove();
			       uploader.removeFile(file);
			       that.removeIndex = false;
			   }
		});
		uploader.on( 'uploadProgress', function( file, percentage ) {
		    var $li = $( '#'+file.id ),
		        $percent = $li.find('.progress span');
		    if ( !$percent.length ) {
		        $percent = $('<p class="progress"><span></span></p>')
		            .appendTo( $li )
		            .find('span');
		    }

		    $percent.css( 'width', percentage * 100 + '%' );
		});
		uploader.on( 'uploadSuccess', function( file,response ) {
		    $( '#'+file.id ).addClass('upload-state-done');
		    that.response = response;
		    that.removeIndex = true;   
		    try{
		    	 callback(response);
		    }catch(err){
		    	
		    }
		});
		uploader.on( 'uploadError', function( file ) {
		    var $li = $( '#'+file.id ),
		        $error = $li.find('div.error');
		    // 避免重复创建
		    if ( !$error.length ) {
		        $error = $('<div class="error"></div>').appendTo( $li );
		    }
		    $error.text('上传失败');
		    that.removeIndex = true;
		});
		uploader.on( 'uploadComplete', function( file ) {
		    $( '#'+file.id ).find('.progress').remove();
		});
		uploader.on("error", function (type) {
		    if (type == "F_TYPE_DENIED") {
		        alert("请上传JPG、PNG、BMP格式文件");
		    } else if (type == "F_EXCEED_SIZE") {
		        alert("文件大小不能超过100KB");
		    }else if(type == "Q_EXCEED_NUM_LIMIT"){
		       alert('只能上传一张图片');
		    }else {
		        alert("上传出错！请检查后重新上传！错误代码"+type);
		    }

		});
	},
	this.upload = function(){
		uploader.upload();
	}
};
UploadIcon.prototype={
		removeIcon:function(file){
			 this.dom.remove();
			 this.newUploader.removeFile(file);
		},
		upload:function(){
			
		}
};

function GetRequest() {
    var url = location.search;
    var theRequest = new Object();
    if (url.indexOf("?") != -1) {
        var str = url.substr(1);
        strs = str.split("&");
        for (var i = 0; i < strs.length; i++) {
            theRequest[strs[i].split("=")[0]] = unescape(strs[i].split("=")[1]);
        }
    }
    return theRequest;
};

//验证
//function Verify(data){
//   this.verData = data;
//   
//}




