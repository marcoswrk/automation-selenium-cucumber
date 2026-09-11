package core;

import lombok.AllArgsConstructor;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Cart {
        private String imageSrc;
        private String description;
        private String price;
        private String quantity;
        private String total;

}
