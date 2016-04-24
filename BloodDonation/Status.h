//
//  Status.h
//  BloodDonation
//
//  Created by Le Phuc on 4/24/16.
//  Copyright © 2016 Lê Phúc. All rights reserved.
//

#import <Foundation/Foundation.h>

@interface Status : NSObject

@property(nonatomic, strong) NSString* caption;
@property(nonatomic, strong) NSString* likeNumber;
@property(nonatomic, strong) NSString* commentNumber;

@property(nonatomic, strong) NSString* timePost;
@property(nonatomic, strong) NSString* userLiked;

@property(nonatomic, strong) NSString* isLike;
@property(nonatomic, strong) NSString* isComment;
@property(nonatomic, strong) NSString* isShare;


@end
