$(document).ready(function(){
    // 회원 이름을 클릭했을 때 회원 정보 수정 창 보이게 하기
    $('#nick').on('click', function(){
        $("#formhidden").toggle();
    });

    // 회원 정보(전화번호 수정)
    $('#modifyBtn').on('click', function(){
        let nickname = $('input[name=name]').val().trim();
        let phoneNumber = $('input[name=phoneNumber]').val().trim();
        let id = $('input[name=id]').val().trim();

        $.ajax({
            type:"PUT"
            ,url:"/users/" + id
            , data:{"nickname":nickname, "phoneNumber":phoneNumber, "id":id}
            , success:function (data){
                if(data.code == 200){
                    alert('정보가 수정되었습니다!');
                } else if (data.code == 500) {
                    alert(data.errorMessage);
                }
            }
            , error:function(request, status, error) {
                alert("수정에 실패했습니다.");
            }
        });
    });
});