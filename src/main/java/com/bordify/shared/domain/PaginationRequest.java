package com.bordify.shared.domain;

import lombok.Data;


@Data
public class PaginationRequest {

        public PaginationRequest(int pageNumber, int pageSize) {
            this.pageNumber = pageNumber;
            this.pageSize = pageSize;
        }

        private int pageNumber;
        private int pageSize;

        public int getOffset() {
            return (pageNumber - 1) * pageSize;
        }

}

