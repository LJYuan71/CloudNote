//单击笔记本加载笔记列表
function loadnotes(){
	//设置全部笔记列表显示,其他列表隐藏
    $("#pc_part_6").hide();//搜索结果列表区
    $("#pc_part_2").show();//全部笔记列表区
    $("#pc_part_5").hide();//笔记预览区
    $("#pc_part_4").hide();//回收站
    $("#pc_part_3").show();//笔记编辑区
    //给笔记本li设置选中样式
    $("#book_list li a").removeClass("checked");
    $(this).find("a").addClass("checked");
    //获取bookId
    var bookId = $(this).data("bookId");
    //发送请求
    $.ajax({
      url:sessionStorage.projectName+"/note/loadnotes.do",
      type:"post",
      data:{"bookId":bookId},
      dataType:"json",
      success:function(result){
          if(result.status==0){
            var notes = result.data;//获取返回的笔记集合
            //清除原有笔记列表li
            $("#note_list").empty();
            //循环添加新的li
            for(var i=0;i<notes.length;i++){
              var noteId = notes[i].cn_note_id;//获取笔记ID
              var noteTitle = notes[i].cn_note_title;//获取笔记标题
              //拼成一个笔记列表的li
              var s_li = '<li class="online">';
              s_li += '<a>';
              s_li += '<i class="fa fa-file-text-o" title="online" rel="tooltip-bottom"></i>'+noteTitle+'<button type="button" class="btn btn-default btn-xs btn_position btn_slide_down"><i class="fa fa-chevron-down"></i></button>';
              s_li += '</a>';
              s_li += '<div class="note_menu" tabindex="-1">';
              s_li += '<dl>';
              s_li += '  <dt><button type="button" class="btn btn-default btn-xs btn_move" title="移动至..."><i class="fa fa-random"></i></button></dt>';
              s_li += '  <dt><button type="button" class="btn btn-default btn-xs btn_share" title="分享"><i class="fa fa-sitemap"></i></button></dt>';
              s_li += '  <dt><button type="button" class="btn btn-default btn-xs btn_delete" title="删除"><i class="fa fa-times"></i></button></dt>';
              s_li += '</dl>';
              s_li += '</div>';
              s_li += '</li>';
              var $li = $(s_li);
              $li.data("noteId",noteId);//给li绑定笔记ID
              //将笔记li添加到笔记ul中
              $("#note_list").append($li);
            }
          }
      }
    });
};

//弹出添加笔记对话框
function showAddNoteWindow(){
    $(".opacity_bg").show();//显示背景
    $("#can").load("alert/alert_note.html");//弹出对话框
};

//确认创建笔记操作
function sureAddNote(){
    //获取笔记名称
    var noteTitle = $("#input_note").val().trim();
    //获取笔记本ID
    var $bookli = $("#book_list a.checked").parent();
    var bookId = $bookli.data("bookId");
    //alert(bookId);
    //TODO 检测格式
    if(bookId==undefined){
        alert("请选择笔记本");
        return;
    }
    //发送请求
    $.ajax({
       url:sessionStorage.projectName+"/note/add.do",
       type:"post",
       data:{"noteTitle":noteTitle,"bookId":bookId,"userId":userId},
       dataType:"json",
       success:function(result){
          if(result.status==0){
             closeWindow();//关闭对话框
             var noteId = result.data;//获取笔记ID
             //拼一个笔记li
              var s_li = '<li class="online">';
              s_li += '<a>';
              s_li += '<i class="fa fa-file-text-o" title="online" rel="tooltip-bottom"></i>'+noteTitle+'<button type="button" class="btn btn-default btn-xs btn_position btn_slide_down"><i class="fa fa-chevron-down"></i></button>';
              s_li += '</a>';
              s_li += '<div class="note_menu" tabindex="-1">';
              s_li += '<dl>';
              s_li += '  <dt><button type="button" class="btn btn-default btn-xs btn_move" title="移动至..."><i class="fa fa-random"></i></button></dt>';
              s_li += '  <dt><button type="button" class="btn btn-default btn-xs btn_share" title="分享"><i class="fa fa-sitemap"></i></button></dt>';
              s_li += '  <dt><button type="button" class="btn btn-default btn-xs btn_delete" title="删除"><i class="fa fa-times"></i></button></dt>';
              s_li += '</dl>';
              s_li += '</div>';
              s_li += '</li>';
              var $li = $(s_li);
              $li.data("noteId",noteId);//给li绑定笔记ID
              //将笔记li添加到笔记列表区
              $("#note_list").prepend($li);
          }
       },
       error:function(){
          alert("创建笔记失败");
       }
    });
};

//单击笔记li时给编辑区加载笔记信息
function loadNote(){
    //设置笔记选中效果
    $("#note_list li a").removeClass("checked");
    $(this).find("a").addClass("checked");
    //获取笔记ID
    var noteId = $(this).data("noteId");
    //发送ajax请求
    $.ajax({
      url:sessionStorage.projectName+"/note/load.do",
      type:"post",
      data:{"noteId":noteId},
      dataType:"json",
      success:function(result){
          if(result.status==0){
              var note = result.data;
              var noteTitle = note.cn_note_title;
              var noteBody = note.cn_note_body;
              //设置编辑区标题
              $("#input_note_title").val(noteTitle);
              //设置编辑区内容
              um.setContent(noteBody);
          }
      },
      error:function(){
         alert("加载笔记失败");
      }
    });
};

//保存笔记按钮处理
function updateNote(){
    //获取笔记标题
    var noteTitle = $("#input_note_title").val().trim();
    //获取笔记内容
    var noteBody = um.getContent();
    //获取笔记ID
    var $noteli = $("#note_list a.checked").parent();
    var noteId = $noteli.data("noteId");
    //TODO格式检查
    //发送Ajax请求
    $.ajax({
      url:sessionStorage.projectName+"/note/update.do",
      type:"post",
      data:{"noteId":noteId,"noteTitle":noteTitle,"noteBody":noteBody},
      dataType:"json",
      success:function(result){
         if(result.status==0){
           //如果标题改变,修改列表li标题
           var liTitle = $("#note_list a.checked").text().trim();
           if(liTitle != noteTitle){//需要修改
             var s = '<i class="fa fa-file-text-o" title="online" rel="tooltip-bottom"></i>'+noteTitle+'<button type="button" class="btn btn-default btn-xs btn_position btn_slide_down"><i class="fa fa-chevron-down"></i></button>';
             $("#note_list a.checked").html(s);//替换选中的<a>元素的内容
           }
           alert("笔记保存成功");
         }
      },
      error:function(){
         alert("笔记保存失败");
      }
    });
 };

//确认将笔记移入回收站
 function recycleNote(){
     //获取笔记ID
     var $li = $("#note_list a.checked").parent();
     var noteId = $li.data("noteId");
     //发送Ajax请求
     $.ajax({
       url:sessionStorage.projectName+"/note/recycle.do",
       type:"post",
       data:{"noteId":noteId},
       dataType:"json",
       success:function(result){
          if(result.status==0){
              //删除笔记li
              $li.remove();
              //清空笔记编辑区
              $("#input_note_title").val("");
              um.setContent("");
              //提示删除成功
              alert(result.msg);
          }
       },
       error:function(){
          alert("删除笔记失败");
       }
     });
 };

 //确认笔记分享操作
 function shareNote(){
     //获取笔记ID
     var $li = $(this).parents("li");
     var noteId = $li.data("noteId");
     //发送Ajax请求
     $.ajax({
       url:sessionStorage.projectName+"/note/share.do",
       type:"post",
       data:{"noteId":noteId},
       dataType:"json",
       success:function(result){
          alert(result.msg);
       },
       error:function(){
          alert("分享笔记失败");
       }
     });

 };
 
//分享笔记检索
 function searchNotes(event){
     var code = event.keyCode;//获取键的ASCII值
     //回车键值为13
     if(code==13){
       //清除原有搜索结果列表
       $("#share_list").empty();
       //获取检索关键字
       var keyword = $("#search_note").val().trim();
       //发送ajax请求
       $.ajax({
         url:sessionStorage.projectName+"/note/search.do",
         type:"post",
         data:{"keyword":keyword},
         dataType:"json",
         success:function(result){
           if(result.status==0){
             var notes = result.data;
             //循环生成检索结果笔记li
             for(var i=0;i<notes.length;i++){
               var shareId = notes[i].cn_share_id;
               var shareTitle = notes[i].cn_share_title;
               //拼成li
               var s_li = '<li class="online">';
               s_li += '<a>';
               s_li += '<i class="fa fa-file-text-o" title="online" rel="tooltip-bottom"></i>'+shareTitle+'<button type="button" class="btn btn-default btn-xs btn_position btn_slide_down"><i class="fa fa-star"></i></button>';
               s_li += '</a>';
               s_li += '</li>';
               var $li = $(s_li);
               $li.data("shareId",shareId);
               //添加到share_list列表中
               $("#share_list").append($li);
             }
             //显示搜索结果div
             $("#pc_part_6").show();//搜索结果列表区
             $("#pc_part_2").hide();//全部笔记列表区
             $("#pc_part_5").show();//笔记预览区
             $("#pc_part_3").hide();//笔记编辑区
             $("#pc_part_4").hide();//回收站
           }
         }
       });
     }
 };
 
//查看搜索分享的笔记信息
 function showShare(){
     //获取shareId
     var shareId = $(this).data("shareId");
     //发送ajax请求
     $.ajax({
        url:sessionStorage.projectName+"/note/loadShare.do",
        type:"post",
        data:{"shareId":shareId},
        dataType:"json",
        success:function(result){
           if(result.status==0){
              var share = result.data;
              $("#noput_note_title").text(share.cn_share_title);
              $("#noput_note_body").html(share.cn_share_body);
           }
        }
     });
 };
 
 
