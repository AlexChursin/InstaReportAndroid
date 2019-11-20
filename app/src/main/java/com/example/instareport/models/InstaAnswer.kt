package com.example.instareport.models

data class InstaAnswer(
    val `data`: Data,
    val status: String
) {
    data class Data(
        val user: User
    ) {
        data class User(
            val edge_owner_to_timeline_media: EdgeOwnerToTimelineMedia
        ) {
            data class EdgeOwnerToTimelineMedia(
                val count: Int,
                val edges: List<Edge>,
                val page_info: PageInfo
            ) {
                data class Edge(
                    val node: Node
                ) {
                    data class Node(
                        val __typename: String,
                        val accessibility_caption: Any,
                        val comments_disabled: Boolean,
                        val dimensions: Dimensions,
                        val display_resources: List<DisplayResource>,
                        val display_url: String,
                        val edge_media_preview_like: EdgeMediaPreviewLike,
                        val edge_media_to_caption: EdgeMediaToCaption,
                        val edge_media_to_comment: EdgeMediaToComment,
                        val edge_media_to_sponsor_user: EdgeMediaToSponsorUser,
                        val edge_media_to_tagged_user: EdgeMediaToTaggedUser,
                        val fact_check_information: Any,
                        val fact_check_overall_rating: Any,
                        val gating_info: Any,
                        val id: String,
                        val is_video: Boolean,
                        val location: Location,
                        val media_preview: String,
                        val owner: Owner,
                        val shortcode: String,
                        val taken_at_timestamp: Int,
                        val thumbnail_resources: List<ThumbnailResource>,
                        val thumbnail_src: String,
                        val tracking_token: String,
                        val viewer_can_reshare: Boolean,
                        val viewer_has_liked: Boolean,
                        val viewer_has_saved: Boolean,
                        val viewer_has_saved_to_collection: Boolean,
                        val viewer_in_photo_of_you: Boolean
                    ) {
                        data class Dimensions(
                            val height: Int,
                            val width: Int
                        )

                        data class DisplayResource(
                            val config_height: Int,
                            val config_width: Int,
                            val src: String
                        )

                        data class EdgeMediaPreviewLike(
                            val count: Int,
                            val edges: List<Any>
                        )

                        data class EdgeMediaToCaption(
                            val edges: List<Edge>
                        ) {
                            data class Edge(
                                val node: Node
                            ) {
                                data class Node(
                                    val text: String
                                )
                            }
                        }

                        data class EdgeMediaToComment(
                            val count: Int,
                            val edges: List<Edge>,
                            val page_info: PageInfo
                        ) {
                            data class Edge(
                                val node: Node
                            ) {
                                data class Node(
                                    val created_at: Int,
                                    val did_report_as_spam: Boolean,
                                    val id: String,
                                    val owner: Owner,
                                    val text: String,
                                    val viewer_has_liked: Boolean
                                ) {
                                    data class Owner(
                                        val id: String,
                                        val is_verified: Boolean,
                                        val profile_pic_url: String,
                                        val username: String
                                    )
                                }
                            }

                            data class PageInfo(
                                val end_cursor: String,
                                val has_next_page: Boolean
                            )
                        }

                        data class EdgeMediaToSponsorUser(
                            val edges: List<Any>
                        )

                        data class EdgeMediaToTaggedUser(
                            val edges: List<Any>
                        )

                        data class Location(
                            val has_public_page: Boolean,
                            val id: String,
                            val name: String,
                            val slug: String
                        )

                        data class Owner(
                            val id: String,
                            val username: String
                        )

                        data class ThumbnailResource(
                            val config_height: Int,
                            val config_width: Int,
                            val src: String
                        )
                    }
                }

                data class PageInfo(
                    val end_cursor: String,
                    val has_next_page: Boolean
                )
            }
        }
    }
}