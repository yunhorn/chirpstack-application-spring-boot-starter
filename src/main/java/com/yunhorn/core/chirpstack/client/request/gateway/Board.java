package com.yunhorn.core.chirpstack.client.request.gateway;

import lombok.Data;

import java.util.Objects;
import java.util.Optional;

/**
 * @author ljm
 * @date 2021/2/25 11:59
 */
@Data
public class Board {
//     "fineTimestampKey":"string",
//             "fpgaID":"string"
    private String fineTimestampKey;
    private String fpgaID;

    @Override
    public boolean equals(Object o) {
        if (o==null || getClass() != o.getClass()) {
            return false;
        }
        Board thisBoard = this;
        Board thatBoard = (Board) o;
        return Optional.ofNullable(thisBoard.getFineTimestampKey()).orElse("").equals(Optional.ofNullable(thatBoard.getFineTimestampKey()).orElse(""))
                && Optional.ofNullable(thisBoard.getFpgaID()).orElse("").equals(Optional.ofNullable(thatBoard.getFpgaID()).orElse(""));
    }

    @Override
    public int hashCode() {
        return Objects.hash(fineTimestampKey, fpgaID);
    }
}
