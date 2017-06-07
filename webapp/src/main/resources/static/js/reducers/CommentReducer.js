import {REPLACE_COMMENTS} from "../actions/types/CommentActionTypes";
import {ADD_COMMENT} from "../actions/types/CommentActionTypes";
import {preloadState} from "../index";


export function commentReducer(state, action) {
    if (state === undefined) {
        return preloadState
    }

    switch (action.type) {
        case REPLACE_COMMENTS: {
            return replaceComments(action.comments);
        }
        case ADD_COMMENT: {
            return addComment(action.comment, state);
        }
        default:
            return [];
    }
    return state;
}

let replaceComments = function (newComments) {
    console.log(newComments)
    console.log("Replace comments")
    return newComments
};


let addComment = function (newComment, comments){
  console.log('addComment');
  let newComments = [];
  for (let i = 0; i < comments.length; i++) {
          newComments.push(comments[i]);
  }
  newComments.push(newComment);
  return newComments;
};
