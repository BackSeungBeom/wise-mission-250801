package com; // 해당 클래스가 속한 패키지 선언

/**
 * 명언(Quote) 정보를 담는 데이터 모델 클래스입니다.
 * 각 명언은 고유한 ID, 내용, 그리고 저자를 가집니다.
 * 명언 관리 시스템 등에서 명언 데이터를 구조화하는 데 활용됩니다.
 */
class Quote {
    private int id;       // 명언의 고유 식별자 (Unique ID)
    private String saying; // 명언의 실제 내용 (Quote text)
    private String author; // 명언의 저자 (Author of the quote)

    /**
     * Quote 객체를 생성하는 생성자입니다.
     * 새로운 명언 데이터를 초기화할 때 사용됩니다.
     *
     * @param id     명언의 고유 ID
     * @param saying 명언의 내용
     * @param author 명언의 저자
     */
    public Quote(int id, String saying, String author) {
        this.id = id;
        this.saying = saying;
        this.author = author;
    }

    /**
     * 명언의 ID를 반환합니다.
     *
     * @return 현재 Quote 객체의 ID
     */
    public int getId() {
        return id;
    }

    /**
     * 명언의 ID를 설정합니다.
     *
     * @param id 새로 설정할 ID
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * 명언의 내용을 반환합니다.
     *
     * @return 현재 Quote 객체의 내용
     */
    public String getSaying() {
        return saying;
    }

    /**
     * 명언의 내용을 설정합니다.
     *
     * @param saying 새로 설정할 내용
     */
    public void setSaying(String saying) {
        this.saying = saying;
    }

    /**
     * 명언의 저자를 반환합니다.
     *
     * @return 현재 Quote 객체의 저자
     */
    public String getAuthor() {
        return author;
    }

    /**
     * 명언의 저자를 설정합니다.
     *
     * @param author 새로 설정할 저자
     */
    public void setAuthor(String author) {
        this.author = author;
    }
}