function userCheck() {   //�û���¼��֤
    if (document.form1.account.value == "") {
        window.alert("�������û�����");
        return false;
    }
    if (document.form1.password.value == "") {
        window.alert("�������û�����");
        return false;
    }
    return true;
}

function accountADD() {   //�û�ע����֤
    if (document.form.account.value == "") {
        window.alert("�������û�����");
        return false;
    }
    if (document.form.password.value == "") {
        window.alert("�������û���¼���룡");
        return false;
    }
    if (document.form.repeatPassword.value == "") {
        window.alert("�������ظ���¼���룡");
        return false;
    }
    if (form.password.value != form.repeatPassword.value) {
        alert("��������������벻һ�£����������룡");
        return false;
    }
    if (document.form.name.value == "") {
        window.alert("�������û�������");
        return false;
    }
    if (document.form.QQnumber.value == "") {
        window.alert("������QQ���룡");
        return false;
    }
    if (isNaN(document.form.QQnumber.value)) {
        window.alert("QQ�������Ϊ���֣�");
        return false;
    }
    if (document.form.mainPage.value == "") {
        window.alert("��������ҳ��ַ��");
        return false;
    }
    if (document.form.interest.value == "") {
        window.alert("��������Ȥ���ݣ�");
        return false;
    }
    if (document.form.eMail.value == "") {
        window.alert("������eMail��ַ���ݣ�");
        return false;
    }
    var i = form.eMail.value.indexOf("@");
    var j = form.eMail.value.indexOf(".");
    if ((i < 0) || (i - j > 0) || (j < 0)) {
        alert("�������Email��ַ����ȷ�����������룡");
        return false;
    }
    return true;
}

function hostUpdate() {   //�û�ע����֤
    if (document.form.account.value == "") {
        window.alert("�������û�����");
        return false;
    }
    if (document.form.password.value == "") {
        window.alert("�������û���¼���룡");
        return false;
    }
    if (document.form.repeatPassword.value == "") {
        window.alert("�������û��ظ����룡");
        return false;
    }
    if (form.password.value != form.repeatPassword.value) {
        alert("��������������벻һ�£����������룡");
        return false;
    }
    if (document.form.name.value == "") {
        window.alert("�������û�������");
        return false;
    }
    if (document.form.QQnumber.value == "") {
        window.alert("������QQ���룡");
        return false;
    }
    if (isNaN(document.form.QQnumber.value)) {
        window.alert("QQ�������Ϊ���֣�");
        return false;
    }
    if (document.form.mainPage.value == "") {
        window.alert("��������ҳ��ַ��");
        return false;
    }
    if (document.form.interest.value == "") {
        window.alert("��������Ȥ���ݣ�");
        return false;
    }
    if (document.form.eMail.value == "") {
        window.alert("������eMail��ַ���ݣ�");
        return false;
    }
    var i = form.eMail.value.indexOf("@");
    var j = form.eMail.value.indexOf(".");
    if ((i < 0) || (i - j > 0) || (j < 0)) {
        alert("�������Email��ַ����ȷ�����������룡");
        return false;
    }
    return true;
}

function friendAdd() {   //�������
    if (document.form.name.value == "") {
        window.alert("�����������ǳƣ�");
        return false;
    }
    if (document.form.QQNumber.value == "") {
        window.alert("������QQ���룡");
        return false;
    }
    if (isNaN(document.form.QQNumber.value)) {
        window.alert("QQ�������Ϊ���֣�");
        return false;
    }
    if (document.form.description.value == "") {
        window.alert("����������������Ϣ��");
        return false;
    }
    return true;
}

function voteAdd() {   //ͶƱ�������
    if (document.form.voteName.value == "") {
        window.alert("������ͶƱ���ݣ�");
        return false;
    }
    return true;
}

function addDiscuss() {   //�������
    if (document.form.discussTitle.value == "") {
        window.alert("������������Ŀ��");
        return false;
    }
    if (document.form.discussContent.value == "") {
        window.alert("�������������ݣ�");
        return false;
    }
    return true;
}

function addPhoto() {   //�������
    if (document.form.photoAddress.value == "") {
        window.alert("��ѡ���ϴ�����Ƭ��");
        return false;
    }
    if (document.form.photoDescription.value == "") {
        window.alert("��������Ƭ��������Ϣ��");
        return false;
    }
    return true;
}

function addArticleType() {   //�����������
    if (document.form.typeName.value == "") {
        window.alert("�����������������ƣ�");
        return false;
    }
    if (document.form.description.value == "") {
        window.alert("��������������������");
        return false;
    }
    return true;
}


function addArticle() {   //�������
    if (document.form.title.value == "") {
        window.alert("������������Ŀ��");
        return false;
    }
    if (document.form.content.value == "") {
        window.alert("�������������ݣ�");
        return false;
    }
    return true;
}

function addRestore() {   //�ظ����
    if (document.form.reTitle.value == "") {
        window.alert("������ظ���Ŀ��");
        return false;
    }
    if (document.form.reContent.value == "") {
        window.alert("������ظ����ݣ�");
        return false;
    }
    return true;
}

function quit() {
    if (confirm("�����Ҫ�˳���")) {
        window.location.href = "leave.jsp";
    }
}

function sendInformation() {   //���ⷢ��
    if (document.form.title.value == "") {
        window.alert("��������������");
        return false;
    }
    if (document.form.content.value == "") {
        window.alert("��������������");
        return false;
    }
    if (form.content.value.length > 1600) {
        alert("��������Ϊ1600λ�����������룡");
        return false;
    }
    return true;
}

function backInformation() {   //����ظ�
    if (document.form.title.value == "") {
        window.alert("������ظ���������");
        return false;
    }
    if (document.form.content.value == "") {
        window.alert("������ظ���������");
        return false;
    }
    if (form.content.value.length > 1600) {
        alert("��������Ϊ1600λ�����������룡");
        return false;
    }
    return true;
}


function registerCheck() {      //���û�ע����֤
    if (form.account.value == "") {
        alert("�������û�����");
        return false;
    }
    if (form.account.value == "MrFriend") {
        window.alert("���û���������");
        return false;
    }
    if (form.realname.value == "") {
        alert("��������ʵ������");
        return false;
    }
    if (form.password1.value == "") {
        alert("���������룡");
        return false;
    }
    if (form.password1.value.length < 6) {
        alert("��������Ϊ6λ�����������룡");
        return false;
    }
    if (form.password2.value == "") {
        alert("������ȷ�����룡");
        return false;
    }
    if (form.password1.value != form.password2.value) {
        alert("��������������벻һ�£����������룡");
        return false;
    }
    if (form.bron.value == "") {
        alert("��������������");
        return false;
    }
    if (CheckDate(form.bron.value)) {
        alert("�������׼���ڣ��磺1980/07/17��1980-07-17��");
        return false;
    }
    if (form.email.value == "") {
        alert("������Email��ַ��");
        return false;
    }
    var i = form.email.value.indexOf("@");
    var j = form.email.value.indexOf(".");
    if ((i < 0) || (i - j > 0) || (j < 0)) {
        alert("�������Email��ַ����ȷ�����������룡");
        return false;
    }
    return true;
}


function CheckDate(INDate) {
    if (INDate == "") {
        return true;
    }
    subYY = INDate.substr(0, 4)
    if (isNaN(subYY) || subYY <= 0) {
        return true;
    }
    //ת���·�
    if (INDate.indexOf('-', 0) != -1) {
        separate = "-"
    }
    else {
        if (INDate.indexOf('/', 0) != -1) {
            separate = "/"
        }
        else {
            return true;
        }
    }
    area = INDate.indexOf(separate, 0)
    subMM = INDate.substr(area + 1, INDate.indexOf(separate, area + 1) - (area + 1))
    if (isNaN(subMM) || subMM <= 0) {
        return true;
    }
    if (subMM.length < 2) {
        subMM = "0" + subMM
    }
    //ת����
    area = INDate.lastIndexOf(separate)
    subDD = INDate.substr(area + 1, INDate.length - area - 1)
    if (isNaN(subDD) || subDD <= 0) {
        return true;
    }
    if (eval(subDD) < 10) {
        subDD = "0" + eval(subDD)
    }
    NewDate = subYY + "-" + subMM + "-" + subDD
    if (NewDate.length != 10) {
        return true;
    }
    if (NewDate.substr(4, 1) != "-") {
        return true;
    }
    if (NewDate.substr(7, 1) != "-") {
        return true;
    }
    var MM = NewDate.substr(5, 2);
    var DD = NewDate.substr(8, 2);
    if ((subYY % 4 == 0 && subYY % 100 != 0) || subYY % 400 == 0) { //�ж��Ƿ�Ϊ����
        if (parseInt(MM) == 2) {
            if (DD > 29) {
                return true;
            }
        }
    } else {
        if (parseInt(MM) == 2) {
            if (DD > 28) {
                return true;
            }
        }
    }
    var mm = new Array(1, 3, 5, 7, 8, 10, 12); //�ж�ÿ���е��������
    for (i = 0; i < mm.length; i++) {
        if (parseInt(MM) == mm[i]) {
            if (parseInt(DD) > 31) {
                return true;
            } else {
                return false;
            }
        }
    }
    if (parseInt(DD) > 30) {
        return true;
    }
    if (parseInt(MM) > 12) {
        return true;
    }
    return false;
}


function Myopen(divID, myform) { //���ݴ��ݵĲ���ȷ����ʾ�Ĳ�
    divID.style.display = '';
    divID.style.left = (screen.width - 240) / 2;
    divID.style.top = (screen.height - 277) / 2;
    myform.UID.focus();
}

function clockon(bgclock) {
    var now = new Date();
    var year = now.getYear();
    var month = now.getMonth();
    var date = now.getDate();
    var day = now.getDay();
    var hour = now.getHours();
    var minu = now.getMinutes();
    var sec = now.getSeconds();
    var week;
    month = month + 1;
    if (month < 10) month = "0" + month;
    if (date < 10) date = "0" + date;
    if (hour < 10) hour = "0" + hour;
    if (minu < 10) minu = "0" + minu;
    if (sec < 10) sec = "0" + sec;
    var arr_week = new Array("������", "����һ", "���ڶ�", "������", "������", "������", "������");
    week = arr_week[day];
    var time = "";
    time = year + "��" + month + "��" + date + "�� " + week + " " + hour + ":" + minu + ":" + sec;
    if (document.all) {
        bgclock.innerHTML = "" + time + ""
    }
    var timer = setTimeout("clockon(bgclock)", 200);
} 




