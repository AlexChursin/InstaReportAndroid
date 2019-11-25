package com.example.instareport.models.answers

data class FirstNameJson(
    val graphql: Graphql,
    val logging_page_id: String,
    val show_follow_dialog: Boolean,
    val show_suggested_profiles: Boolean,
    val toast_content_on_load: Any
) {
    data class Graphql(
        val user: User
    ) {
        data class User(
            val biography: String,
            val blocked_by_viewer: Boolean,
            val business_category_name: Any,
            val connected_fb_page: Any,
            val country_block: Boolean,
            val edge_felix_video_timeline: EdgeFelixVideoTimeline,
            val edge_follow: EdgeFollow,
            val edge_followed_by: EdgeFollowedBy,
            val edge_media_collections: EdgeMediaCollections,
            val edge_mutual_followed_by: EdgeMutualFollowedBy,
            val edge_owner_to_timeline_media: EdgeOwnerToTimelineMedia,
            val edge_saved_media: EdgeSavedMedia,
            val external_url: String,
            val external_url_linkshimmed: String,
            val followed_by_viewer: Boolean,
            val follows_viewer: Boolean,
            val full_name: String,
            val has_blocked_viewer: Boolean,
            val has_channel: Boolean,
            val has_requested_viewer: Boolean,
            val highlight_reel_count: Int,
            val id: String,
            val is_business_account: Boolean,
            val is_joined_recently: Boolean,
            val is_private: Boolean,
            val is_verified: Boolean,
            val profile_pic_url: String,
            val profile_pic_url_hd: String,
            val requested_by_viewer: Boolean,
            val username: String
        ) {
            data class EdgeFelixVideoTimeline(
                val count: Int,
                val edges: List<Any>,
                val page_info: PageInfo
            ) {
                data class PageInfo(
                    val end_cursor: Any,
                    val has_next_page: Boolean
                )
            }

            data class EdgeFollow(
                val count: Int
            )

            data class EdgeFollowedBy(
                val count: Int
            )

            data class EdgeMediaCollections(
                val count: Int,
                val edges: List<Any>,
                val page_info: PageInfo
            ) {
                data class PageInfo(
                    val end_cursor: Any,
                    val has_next_page: Boolean
                )
            }

            data class EdgeMutualFollowedBy(
                val count: Int,
                val edges: List<Any>
            )

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
                        val accessibility_caption: String,
                        val comments_disabled: Boolean,
                        val dimensions: Dimensions,
                        val display_url: String,
                        val edge_liked_by: EdgeLikedBy,
                        val edge_media_preview_like: EdgeMediaPreviewLike,
                        val edge_media_to_caption: EdgeMediaToCaption,
                        val edge_media_to_comment: EdgeMediaToComment,
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
                        val thumbnail_src: String
                    ) {
                        data class Dimensions(
                            val height: Int,
                            val width: Int
                        )

                        data class EdgeLikedBy(
                            val count: Int
                        )

                        data class EdgeMediaPreviewLike(
                            val count: Int
                        )

                        data class EdgeMediaToCaption(
                            val edges: List<Any>
                        )

                        data class EdgeMediaToComment(
                            val count: Int
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

            data class EdgeSavedMedia(
                val count: Int,
                val edges: List<Any>,
                val page_info: PageInfo
            ) {
                data class PageInfo(
                    val end_cursor: Any,
                    val has_next_page: Boolean
                )
            }
        }
    }
}