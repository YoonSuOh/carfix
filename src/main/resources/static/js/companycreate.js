$(document).ready(function(){
    //  업체 정보 등록
    $('#createBtn').on('click', function(){
        let company = $('input[name=company]').val().trim();
        let ceo = $('input[name=ceo]').val().trim();
        let tel = $('input[name=tel]').val().trim();
        let id = $('input[name=id]').val().trim();
        let address = $('input[name=address]').val().trim();
        let introduce = $('input[name=introduce]').val().trim();

        alert(id);

        $.ajax({
            type:"POST"
            ,url:"/companys"
            , data:{"id":id, "company":company, "ceo":ceo, "tel":tel, "address":address, "introduce":introduce }
            , success:function (data){
                if(data.code == 200){
                    alert('등록 요청이 완료되었습니다!');
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