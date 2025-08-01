package com;

/**
 * 명언(Quote) 정보를 나타내는 데이터 모델 클래스입니다.
 * 각 명언의 고유 ID, 내용, 그리고 저자 정보를 포함합니다.
 */
class Quote {
    private int id;     // 명언의 고유 식별 번호입니다.
    private String saying; // 명언의 실제 내용을 저장합니다.
    private String author; // 명언을 작성하거나 언급한 사람의 이름을 저장합니다.

    /**
     * 새로운 {@code Quote} 객체를 생성하는 생성자입니다.
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

    /**
     * 명언의 ID를 반환합니다.
     * @return 현재 명언의 ID
     */
    public int getId() { return id; }

    /**
     * 명언의 ID를 설정합니다.
     * @param id 새로 설정할 ID
     */
    public void setId(int id) { this.id = id; }

    /**
     * 명언의 내용을 반환합니다.
     * @return 현재 명언의 내용
     */
    public String getSaying() { return saying; }

    /**
     * 명언의 내용을 설정합니다.
     * @param saying 새로 설정할 내용
     */
    public void setSaying(String saying) { this.saying = saying; }

    /**
     * 명언의 저자를 반환합니다.
     * @return 현재 명언의 저자
     */
    public String getAuthor() { return author; }

    /**
     * 명언의 저자를 설정합니다.
     * @param author 새로 설정할 저자
     */
    public void setAuthor(String author) { this.author = author; }
}