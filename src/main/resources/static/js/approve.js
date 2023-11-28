$(document).ready(function(){
    $('.approveBtn').on('click', function(e){
        e.preventDefault();
        let id = $(this).data('id');
        $.ajax({
            type:"PUT"
            ,url:"/admin/companys/" + id
            , data:{"id":id}
            , success:function (data){
                if(data.code == 200){
                    alert('승인 처리되었습니다.');
                    location.reload(true);
                } else if (data.code == 500) {
                    alert(data.errorMessage);
                }
            }
            , error:function(request, status, error) {
                alert("수정에 실패했습니다.");
            }
        });
    });

    // 승인 취소
    $('.cancelBtn').on('click', function(e){
        e.preventDefault();
        let id = $(this).data('id');
        $.ajax({
            type:"PUT"
            ,url:"/admin/companys/" + id
            , data:{"id":id}
            , success:function (data){
                if(data.code == 200){
                    alert('취소 처리되었습니다.');
                    location.reload(true);
                } else if (data.code == 500) {
                    alert(data.errorMessage);
                }
            }
            , error:function(request, status, error) {
                alert("수정에 실패했습니다.");
            }
        });
    });
})