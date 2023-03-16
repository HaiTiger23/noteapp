package htg.haitran.ghichu;

public class Note {
    private int id;
    private String tieuDe;
    private String noiDung;
    private  String createTime;

    public Note(int id, String tieuDe, String noiDung, String createTime) {
        this.id = id;
        this.tieuDe = tieuDe;
        this.noiDung = noiDung;
        this.createTime = createTime;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTieuDe() {
        return tieuDe;
    }

    public void setTieuDe(String tieuDe) {
        this.tieuDe = tieuDe;
    }

    public String getNoiDung() {
        return noiDung;
    }

    public void setNoiDung(String noiDung) {
        this.noiDung = noiDung;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }
}
