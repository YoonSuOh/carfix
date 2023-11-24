$(document).ready(function(){
    // 회원 이름을 클릭했을 때 회원 정보 수정 창 보이게 하기
    $('#nick').on('click', function(){
        $("#formhidden").toggle();
    });
});