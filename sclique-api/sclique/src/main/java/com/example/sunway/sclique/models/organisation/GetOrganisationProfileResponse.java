package com.example.sunway.sclique.models.organisation;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class GetOrganisationProfileResponse {
    private String organisationId;
    private String name;
    private String description;
    private List<CommitteeMemberDto> committeeMembers;
    private ImageDto coverImage;
    private ImageDto profileImage;

    @Getter
    @Setter
    public static class CommitteeMemberDto {
        private ImageDto avatar;
        private Long memberId;
        private String name;
        private String position;
        private String email;
        private String phoneNumber;
        private ImageDto image;
        private Long managerId;
    }

    @Getter
    @Setter
    public static class ImageDto {
        private String mimeType;
        private String imageDataBase64;
    }
}
