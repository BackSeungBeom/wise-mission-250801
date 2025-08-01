package com;

/**
 * 명언(Quote) 정보를 담는 데이터 모델 클래스입니다.
 * 각 명언은 고유한 ID, 내용, 그리고 저자를 가집니다.
 * 이 클래스는 DTO 또는 도메인 모델로 사용될 수 있습니다.
 */
class Quote {
    private int id;
    private String saying;
    private String author;

    /**
     * Quote 객체를 생성하는 생성자입니다.
     *
     * @param id     명언의 고유 ID
     * @param saying 명언의 내용
     * @param author 명언의 저자
     */
    public Quote(int id, String saying, String author){
        this.id = id;
        this.saying = saying;
        this.author = author;
    }

    /** 명언의 ID를 반환합니다. */
    public int getId() { return id; }
    /** 명언의 ID를 설정합니다. */
    public void setId(int id) { this.id = id; }
    /** 명언의 내용을 반환합니다. */
    public String getSaying() { return saying; }
    /** 명언의 내용을 설정합니다. */
    public void setSaying(String saying) { this.saying = saying; }
    /** 명언의 저자를 반환합니다. */
    public String getAuthor() { return author; }
    /** 명언의 저자를 설정합니다. */
    public void setAuthor(String author) { this.author = author; }
}