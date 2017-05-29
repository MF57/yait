import {REPLACE_COMMENTS} from "./types/CommentActionTypes";
import {ADD_COMMENT} from "./types/CommentActionTypes";

export function replaceComments(newComments) {
    return {
        type: REPLACE_COMMENTS,
        comments: newComments
    }
}

export function addComment(newComment) {
    return {
        type: ADD_COMMENT,
        comment: newComment
    }
}
