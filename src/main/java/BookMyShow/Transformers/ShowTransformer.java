package BookMyShow.Transformers;

import BookMyShow.RequestDtos.AddShowRequest;
import BookMyShow.entities.Show;

public class ShowTransformer {

    public static Show convertAddRequestToEntity(AddShowRequest addShowRequest)
    {
        Show showObj=Show.builder()
                .showDate(addShowRequest.getShowDate())
                .showTime(addShowRequest.getShowTime())
                .build();
        return showObj;
    }
}
